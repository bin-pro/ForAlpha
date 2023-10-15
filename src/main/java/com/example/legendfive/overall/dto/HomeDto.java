package com.example.legendfive.overall.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class HomeDto {


    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class volumeReseponseDto{
        private List<volumeDto> volumeDto;
    }

    @Data
    public static class volumeDto{
        private String stockName;
        private String stockCode;
        private String dataLank;
        private String volume;
        private String stockPresentPrice;
        private String StockDodPercentage;
    }
}
