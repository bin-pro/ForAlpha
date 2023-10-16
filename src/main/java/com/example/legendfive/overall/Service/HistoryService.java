package com.example.legendfive.overall.Service;

import com.example.legendfive.overall.Entity.PredictionRecord;
import com.example.legendfive.overall.dto.HistoryDto;
import com.example.legendfive.overall.repository.PredictionRecordRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class HistoryService {
    private final PredictionRecordRepository predictionRecordRepository;

    @Transactional
    public List<HistoryDto.PredictionHistoryResponseDto> getPredictionHistory(UUID userUuid) {
        List<PredictionRecord> predictionRecords = predictionRecordRepository.findAllByUserUserId(userUuid);

        return predictionRecords.stream()
                .map(record -> HistoryDto.PredictionHistoryResponseDto.builder()
                        .stockName(record.getStock().getStockName())
                        .createdAt(record.getCreatedAt())
                        .endDay(record.getEndDay())
                        .earnedPoint(record.getEarnedPoint())
                        .stockReturns(record.getStockIncreaseRate())
                        .build())
                .collect(Collectors.toList());
    }
}
