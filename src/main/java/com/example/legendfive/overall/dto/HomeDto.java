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
    public static class volumeListDto{
        private String stockName;
        private String stockCode;
        private String dataLank;
        private String volume;
        private String stockPresentPrice;
        private String StockDodPercentage;
    }
}
