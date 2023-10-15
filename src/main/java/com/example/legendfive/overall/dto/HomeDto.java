package com.example.legendfive.overall.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import java.util.List;

public class HomeDto {


    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class volumeReseponseDto{
        private List<volumeDto> trading_volumes;
    }

    @Data
    public static class volumeDto{
        private String stock_name;
        private String data_rank;
        private String stock_present_price;
        private String stock_dod_percentage;
        private String volumeIncreaseRate;
    }
}
