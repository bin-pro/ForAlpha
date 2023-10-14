package com.example.legendfive.overall.controller;

import com.example.legendfive.common.response.ResponseDto;
import com.example.legendfive.overall.Service.QuizService;
import com.example.legendfive.overall.Service.UserService;
import com.example.legendfive.overall.dto.QuizDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/quizes")
@RequiredArgsConstructor
@Slf4j
public class QuizController {
    private final QuizService quizService;
    private final UserService userService;
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

    @PostMapping("/{quiz_id}/users/{user_id}") // 랜덤 퀴즈에 대한 답을 제출받음
    public ResponseEntity<ResponseDto> recordQuiz(@PathVariable("quiz_id") UUID quizId,
                                                  @PathVariable("user_id") UUID userId,
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


    @GetMapping("/{quiz_id}/answer") // 제출받은 답에 대한 결과를 반환
    public ResponseEntity<ResponseDto> getQuizById(@PathVariable Long quiz_id) {
        try {

            QuizDto.QuizAnswerResponseDto answerResponseDto = quizService.getQuizAnswerResponse(quiz_id);

            ResponseDto responseDto2 = ResponseDto.builder()
                    .payload(objectMapper.convertValue(answerResponseDto, Map.class))
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(responseDto2);

        } catch (Exception e) {
            ResponseDto responseDto = ResponseDto.builder().error("Quiz not found with ID: " + quiz_id).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        }
    }

}