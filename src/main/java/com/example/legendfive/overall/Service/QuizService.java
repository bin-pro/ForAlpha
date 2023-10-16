package com.example.legendfive.overall.Service;

import com.example.legendfive.overall.Entity.Quiz;
import com.example.legendfive.overall.Entity.QuizRecord;
import com.example.legendfive.overall.Entity.User;
import com.example.legendfive.overall.dto.QuizDto;
import com.example.legendfive.overall.repository.QuizRecordRepository;
import com.example.legendfive.overall.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.*;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class QuizService {
    private final QuizRecordRepository quizRecordRepository;
    private final UserRepository userRepository;

    public QuizDto.RandomQuizResponseDto getRandomQuiz() {
        // 여기에서 Enum으로 정의된 퀴즈 중 랜덤으로 선택하는 로직을 구현
        Quiz randomQuiz = getRandomQuizFromEnum();

        QuizDto.RandomQuizResponseDto randomResponseDto = QuizDto.RandomQuizResponseDto.builder()
                .id(randomQuiz.ordinal() + 1) // Enum 순서대로 1부터 번호 매김
                .quizQuestion(randomQuiz.getQuestion())
                .build();

        return randomResponseDto;
    }

    private Quiz getRandomQuizFromEnum() {
        int randomIndex = new Random().nextInt(Quiz.values().length);
        return Quiz.values()[randomIndex];
    }

    @Transactional
    public QuizDto.QuizRecordResponseDto recordQuizResponse(UUID userUuid, Quiz quiz, boolean quizAnswer) {
        User user = userRepository.findByUserId(userUuid)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // 오늘 날짜
        LocalDate today = LocalDate.now();

        // 하루에 풀 수 있는 최대 퀴즈 수
        int maxQuizzesPerDay = 2;

        // 오늘 푼 퀴즈 수 확인
        int todayQuizCount = quizRecordRepository.countByUserIdAndCreatedAt(user.getId(), today);

        // 중복 기록 확인
        if (todayQuizCount <= maxQuizzesPerDay) {
            // 퀴즈 기록 생성
            QuizRecord quizRecord = QuizRecord.builder()
                    .user(user)
                    .quiz(quiz)
                    .isWon(quizAnswer)
                    .build();

            quizRecordRepository.save(quizRecord);

            // 사용자의 포인트 업데이트
            int quizPoint = quiz.getPoint();
            User updatedUser = user.toBuilder()
                    .userPoint(user.getUserPoint() + (quizAnswer ? quizPoint : 0))
                    .build();

            // 포인트 업데이트된 사용자 정보 저장
            userRepository.save(updatedUser);

            // 응답 DTO 생성
            return QuizDto.QuizRecordResponseDto.builder()
                    .message("Quiz response recorded successfully.")
                    .build();
        } else {
            // 이미 기록된 경우 응답 생성
            return QuizDto.QuizRecordResponseDto.builder()
                    .message("You can't solve more than 3 quizzes per day.")
                    .build();
        }
    }

    public QuizDto.QuizAnswerResponseDto getQuizAnswerResponse(int quizId) {
        Quiz quiz = getQuizById(quizId);
        return QuizDto.QuizAnswerResponseDto.builder()
                .quizPoint(quiz.getPoint())
                .quizQuestion(quiz.getQuestion())
                .quizAnswer(quiz.getAnswer())
                .quizExplanation(quiz.getExplanation())
                .build();
    }


    private Quiz getQuizById(int quizId) {
        for (Quiz quiz : Quiz.values()) {
            if (quiz.ordinal() == quizId - 1) {
                return quiz;
            }
        }
        throw new IllegalArgumentException("Invalid quiz ID");
    }

    @Transactional(readOnly = true)
    public List<QuizDto.QuizHistoryResponseDto> getQuizHistory(UUID userUuid) {
        List<QuizDto.QuizHistoryResponseDto> quizHistoryList = new ArrayList<>();
        Long userId = userRepository.findIdByUserUuid(userUuid)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        List<QuizRecord> quizRecords = quizRecordRepository.findByUserId(userId);

        for (QuizRecord quizRecord : quizRecords) {
            boolean quizAnswer = quizRecord.isWon();
            Quiz quiz = quizRecord.getQuiz();

            quizHistoryList.add(QuizDto.QuizHistoryResponseDto.builder()
                    .createdAt(quizRecord.getCreatedAt())
                    .quizPoint(quizAnswer ? quiz.getPoint() : 0)
                    .quizQuestion(quiz.getQuestion())
                    .quizAnswer(quiz.getAnswer())
                    .build());
        }

        return quizHistoryList;
    }
}