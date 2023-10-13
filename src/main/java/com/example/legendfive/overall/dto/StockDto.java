package com.example.legendfive.overall.dto;

import com.example.legendfive.overall.Entity.Stock;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
public class StockDto {

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class StockListDto{
        private String email;
        private String title;
        private String content;
    }

    public static class stockSearchResponesDto{
        private String name;
    }

    @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class StockSearchRequestDto{
        private String name;
    }

}
