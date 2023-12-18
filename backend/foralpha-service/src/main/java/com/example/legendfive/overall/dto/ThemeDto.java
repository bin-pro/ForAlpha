package com.example.legendfive.overall.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
public class ThemeDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class ThemeCardListResponseDto{
        private String themeName;
        private LocalDate createdAt;
        private Long themeCount;
        private String userNickname;
    }
}
