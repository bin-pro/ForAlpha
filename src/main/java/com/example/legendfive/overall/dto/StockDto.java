package com.example.legendfive.overall.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public class StockDto {

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class StockListDto{
        private String email;
        private String title;
        private String content;
    }


    @Getter
    @Builder @NoArgsConstructor @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class stockDetailResponseDto{


        private String stock_code;
        private String stock_name;
        private String stock_theme_code;
        private String stock_present_price;
        private String stock_dod_percentage;
        private String stock_min_30;
        private String stock_max_30;
        private List<PriceDataDto> price_data;
        private List<MaLineDataDto> ma_line_data;
    }

    @Getter
    @Builder @NoArgsConstructor @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class stockPresentPriceInS3Dto{

        private String stock_code;
        private String stock_name;
        private String stock_theme_code;
        private String stock_present_price;
    }

    @Getter
    @Builder @NoArgsConstructor @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class stockPredictionRequsetDto{

        @NotNull
        private String investment_period;
        @NotNull
        private String stock_present_price;
    }

    @Getter
    @Builder @NoArgsConstructor @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class stockPredictionResponseDto{

        private String message;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class SearchStockBrandResponseDto{
        private UUID stockUuid;
        private String StockName;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class SearchStockThemeResponseDto{
        private UUID stockUuid;
        private String StockName;
    }
}
