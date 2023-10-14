package com.example.legendfive.overall.Service;

import com.example.legendfive.overall.Entity.Quiz;
import com.example.legendfive.overall.Entity.QuizRecord;
import com.example.legendfive.overall.Entity.User;
import com.example.legendfive.overall.dto.QuizDto;
import com.example.legendfive.overall.repository.QuizRecordRepository;
import com.example.legendfive.overall.repository.QuizRepository;
import com.example.legendfive.overall.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@AllArgsConstructor
public class QuizService {

    @Autowired
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    private final QuizRecordRepository quizRecordRepository;

    private final Random random = new Random();
    public QuizDto.RandomQuizResponseDto getRandomQuiz() {
        Long randomQuizId = generateRandomIdInRange(1L, 2L);
        Quiz randomQuiz = quizRepository.findById(randomQuizId)
                .orElseThrow(() -> new NoSuchElementException("No quiz found with ID: " + randomQuizId));

        QuizDto.RandomQuizResponseDto randomResponseDto = QuizDto.RandomQuizResponseDto.builder()
                .id(randomQuiz.getId())
                .quizQuestion(randomQuiz.getQuizQuestion())
                .build();

        return randomResponseDto;
    }

    // 1에서 15 사이의 랜덤한 Long 값 생성 메서드
    private Long generateRandomIdInRange(Long min, Long max) {
        return min + (long) (Math.random() * (max - min + 1));
    }


    public QuizDto.QuizRecordResponseDto recordQuizResponse(UUID user, UUID quiz, boolean quizAnswer){

        System.out.println("user: " + user + ", quiz: " + quiz + ", quizAnswer: " + quizAnswer);

        User findUser = userRepository.findByUserUuid(user).orElseThrow();
        Quiz findQuiz = quizRepository.findByQuizUuId(quiz).orElseThrow();

        QuizRecord quizRecord = QuizRecord.builder()
                .user(findUser)
                .quiz(findQuiz)
                .isWon(quizAnswer)
                .build();

        quizRecordRepository.save(quizRecord);

        QuizDto.QuizRecordResponseDto quizRecordResponseDto = QuizDto.QuizRecordResponseDto.builder()
                .message("Quiz response recorded successfully.")
                .build();

        return quizRecordResponseDto;
    }


    public QuizDto.QuizAnswerResponseDto getQuizAnswerResponse(UUID quiz_uuid) {
        Quiz findQuiz = quizRepository.findByQuizUuId(quiz_uuid).orElseThrow();

        QuizDto.QuizAnswerResponseDto responseDto = QuizDto.QuizAnswerResponseDto.builder()
                .quizPoint(findQuiz.getQuizPoint())
                .quizQuestion(findQuiz.getQuizQuestion())
                .quizAnswer(findQuiz.isQuizAnswer())
                .quizExplanation(findQuiz.getQuizExplanation())
                .build();

        return responseDto;

    }


    @Transactional(readOnly = true)
    public List<QuizDto.QuizHistoryResponseDto> getQuizHistory(UUID userUuid) {
        // 퀴즈 내역을 담을 리스트
        List<QuizDto.QuizHistoryResponseDto> quizHistoryList = new ArrayList<>();
        Long userId = userRepository.findIdByUserUuid(userUuid).orElseThrow();

        // 사용자의 퀴즈 기록 가져오기
        List<QuizRecord> quizRecords = quizRecordRepository.findByUserId(userId);

        // 각 퀴즈 기록에 대한 정보 추출
        for (QuizRecord quizRecord : quizRecords) {
            Long quizId = quizRecord.getQuiz().getId();
            String quizQuestion = quizRecord.getQuiz().getQuizQuestion();
            boolean quizAnswer = quizRecord.getQuiz().isQuizAnswer();
            int quizPoint = quizRecord.isWon() ? quizRecord.getQuiz().getQuizPoint() : 0;
            LocalDateTime createdAt = quizRecord.getCreatedAt();

            // DTO에 정보 추가
            QuizDto.QuizHistoryResponseDto quizHistoryResponseDto = QuizDto.QuizHistoryResponseDto.builder()
                    .createdAt(createdAt)
                    .quizPoint(quizPoint)
                    .quizQuestion(quizQuestion)
                    .quizAnswer(quizAnswer)
                    .build();

            // 리스트에 추가
            quizHistoryList.add(quizHistoryResponseDto);
        }

        return quizHistoryList;
    }
}