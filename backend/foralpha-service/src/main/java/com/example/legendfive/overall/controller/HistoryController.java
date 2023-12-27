
package com.example.legendfive.overall.controller;


import com.example.legendfive.overall.Service.HistoryService;
import com.example.legendfive.overall.dto.HistoryDto;
import com.example.legendfive.overall.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/foralpha-service/history")
public class HistoryController {
    private final HistoryService historyService;
    @GetMapping
    public ResponseEntity<ResponseDto> getPredictionHistory(@RequestParam("user-uuid") UUID userUuid) {
        try {
            List<HistoryDto.PredictionHistoryResponseDto> predictionHistory = historyService.getPredictionHistory(userUuid);

            Map<String, Object> payload = new HashMap<>();
            payload.put("predictionHistory", predictionHistory);

            ResponseDto responseBody = ResponseDto.builder()
                    .payload(payload)
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        } catch (RuntimeException e) {
            ResponseDto errorResponse = ResponseDto.builder()
                    .error(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}
