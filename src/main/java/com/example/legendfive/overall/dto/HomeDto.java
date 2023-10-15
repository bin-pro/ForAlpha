package com.example.legendfive.overall.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class HomeDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class popularStockResponseDto{
        private String stockName;
        private int predictCount;
    }
}
