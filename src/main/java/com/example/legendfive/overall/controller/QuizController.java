package com.example.legendfive.overall.controller;

import com.example.legendfive.common.response.ResponseDto;
import com.example.legendfive.overall.Entity.Quiz;
import com.example.legendfive.overall.Service.QuizService;
import com.example.legendfive.overall.dto.QuizDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/foralpha-service")
public class QuizController {
    private final QuizService quizService;
    private final ObjectMapper objectMapper;

    @GetMapping("/point/quiz")
    public ResponseEntity<ResponseDto> getRandomQuiz() {
        try {
            QuizDto.RandomQuizResponseDto randomQuizResponseDto = quizService.getRandomQuiz();

            ResponseDto responseDto = ResponseDto.builder()
                    .payload(objectMapper.convertValue(randomQuizResponseDto, Map.class))
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);

        } catch (Exception e) {
            ResponseDto responseDto = ResponseDto.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
        }
    }



    @PostMapping("/point/quiz")
    public ResponseEntity<ResponseDto> recordQuiz(@RequestParam("quiz_id") int quizId,
                                                  @RequestParam("user_id") UUID userUuId,
                                                  @RequestParam boolean quizAnswer) {
        try {
            // Enum 해당하는 퀴즈 가져오기
            Quiz quiz = Quiz.values()[quizId - 1];

            QuizDto.QuizRecordResponseDto quizRecordResponseDto = quizService.recordQuizResponse(userUuId, quiz, quizAnswer);

            ResponseDto responseDto2 = ResponseDto.builder()
                    .payload(objectMapper.convertValue(quizRecordResponseDto, Map.class))
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(responseDto2);
        } catch (Exception e) {
            ResponseDto responseDto = ResponseDto.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
        }
    }


    @GetMapping("/point/quiz/answer")
    public ResponseEntity<ResponseDto> getQuizById(@RequestParam int quizId) {
        try {
            QuizDto.QuizAnswerResponseDto answerResponseDto = quizService.getQuizAnswerResponse(quizId);

            ResponseDto responseDto = ResponseDto.builder()
                    .payload(objectMapper.convertValue(answerResponseDto, Map.class))
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch (IllegalArgumentException e) {
            ResponseDto responseDto = ResponseDto.builder().error("Invalid quiz ID").build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        }
    }
//
    @GetMapping("/profile/history/quiz")
    public ResponseEntity<ResponseDto> getQuizHistory(@RequestParam("user-uuid") UUID userUuid) {
        try {
            List<QuizDto.QuizHistoryResponseDto> quizHistory = quizService.getQuizHistory(userUuid);

            Map<String, List<QuizDto.QuizHistoryResponseDto>> payload = Collections.singletonMap("quizHistory", quizHistory);

            ResponseDto successResponse = ResponseDto.builder()
                    .payload(objectMapper.convertValue(payload, Map.class))
                    .build();

            return ResponseEntity.ok(successResponse);
        } catch (Exception e) {
            ResponseDto errorResponse = ResponseDto.builder()
                    .error(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}