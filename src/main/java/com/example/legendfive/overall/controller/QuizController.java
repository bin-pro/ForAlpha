package com.example.legendfive.overall.controller;

import com.example.legendfive.common.response.ResponseDto;
import com.example.legendfive.overall.Service.QuizService;
import com.example.legendfive.overall.Service.UserService;
import com.example.legendfive.overall.dto.FriendDto;
import com.example.legendfive.overall.dto.QuizDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
@Slf4j
public class QuizController {
    private final QuizService quizService;
    private final ObjectMapper objectMapper;


    @GetMapping
    public ResponseEntity<ResponseDto> getRandomQuiz() {
        System.out.println("Random quiz");
        try{
            QuizDto.RandomQuizResponseDto randomQuizResponseDto = quizService.getRandomQuiz();

            ResponseDto responseDto2 = ResponseDto.builder()
                    .payload(objectMapper.convertValue(randomQuizResponseDto, Map.class))
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(responseDto2);

        }catch(Exception e){
            ResponseDto responseDto = ResponseDto.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
        }
    }

    @PostMapping // 랜덤 퀴즈에 대한 답을 제출받음
    public ResponseEntity<ResponseDto> recordQuiz(@RequestParam("quiz_id") UUID quizId,
                                                  @RequestParam("user_id") UUID userId,
                                                  @RequestParam boolean quizAnswer) {
        try {
            System.out.println("quizId: " + quizId + ", userId: " + userId + ", quizAnswer: " + quizAnswer);
            QuizDto.QuizRecordResponseDto quizRecordResponseDto = quizService.recordQuizResponse(userId, quizId, quizAnswer);

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


    @GetMapping("/answer") // 제출받은 답에 대한 결과를 반환
    public ResponseEntity<ResponseDto> getQuizById(@RequestParam UUID quiz_uuid) {
        try {

            QuizDto.QuizAnswerResponseDto answerResponseDto = quizService.getQuizAnswerResponse(quiz_uuid);

            ResponseDto responseDto2 = ResponseDto.builder()
                    .payload(objectMapper.convertValue(answerResponseDto, Map.class))
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(responseDto2);

        } catch (Exception e) {
            ResponseDto responseDto = ResponseDto.builder().error("Quiz not found with ID").build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        }
    }

    @GetMapping("/history/quiz")
    public ResponseEntity<ResponseDto> getQuizHistory(@RequestParam("user-uuid") UUID userUuid) {
        try {
            List<QuizDto.QuizHistoryResponseDto> quizHistory = quizService.getQuizHistory(userUuid);
            ResponseDto successResponse = ResponseDto.builder()
                    .payload((Map<String, ?>) quizHistory)
                    .build();
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        } catch (RuntimeException e) {
            ResponseDto errorResponse = ResponseDto.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}