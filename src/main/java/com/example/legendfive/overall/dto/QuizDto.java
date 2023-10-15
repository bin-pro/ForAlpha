package com.example.legendfive.overall.dto;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizDto {
    private Long id;
    private UUID quizId;
    private int quizPoint;
    private String quizQuestion;
    private String quizAnswer;
    private String quizExplanation;

    @Getter
    @Builder @NoArgsConstructor @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class RandomQuizResponseDto{
        private UUID quizId;//->안썻음
        private long id;//
        private String quizQuestion;

    }

    @Getter
    @Builder @NoArgsConstructor @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class QuizAnswerRequestDto{
        private UUID userId;
        private UUID quizId;
        private boolean quizAnswer;
    }

    @Getter
    @Builder @NoArgsConstructor @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class QuizAnswerResponseDto{
        private int quizPoint;
        private String quizQuestion;
        private String quizAnswer;
        private String quizExplanation;
    }

    @Getter
    @Builder @NoArgsConstructor @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class QuizRecordResponseDto{
        private String message;
    }

    @Getter
    @Builder @NoArgsConstructor @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class QuizHistoryResponseDto{
        private LocalDateTime createdAt;
        private int quizPoint;
        private String quizQuestion;
        private String quizAnswer;
    }
}