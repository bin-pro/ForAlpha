package com.example.legendfive.overall.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String userId;
    private String nickname;
    private int userPoint;
    private int regionCount;

    @Getter
    @Builder @NoArgsConstructor @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class GetUserByIdRequestDto {
        private Long id;
    }
}