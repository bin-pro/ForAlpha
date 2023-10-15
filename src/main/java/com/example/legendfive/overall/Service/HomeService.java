package com.example.legendfive.overall.Service;


import com.example.legendfive.overall.Entity.Stock;
import com.example.legendfive.overall.dto.HomeDto;
import com.example.legendfive.overall.repository.PredictionRecordRepository;
import com.example.legendfive.overall.repository.stock.StockRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class HomeService {
    private final PredictionRecordRepository predictionRecordRepository;
    private final StockRepository stockRepository;

    public List<HomeDto.popularStockResponseDto> getPopularStocks() {
        List<Long> allStockIds = predictionRecordRepository.findStockIdsOrderByCountDesc();
        List<Stock> popularStocks = stockRepository.findByIdIn(allStockIds);

        return popularStocks.stream()
                .map(stock -> HomeDto.popularStockResponseDto.builder()
                        .stockName(stock.getStockName())
                        .predictCount(getPredictCount(allStockIds, stock.getId()))
                        .build())
                .collect(Collectors.toList());
    }

    private int getPredictCount(List<Long> allStockIds, Long stockId) {
        return (int) allStockIds.stream().filter(id -> id.equals(stockId)).count();
    }

//    public List<HomeDto.popularStockResponseDto> getPopularStocks() {
//        List<Long> topStockIds = predictionRecordRepository.findTop30StockIdsOrderByCountDesc();
//        List<Stock> popularStocks = stockRepository.findByIdIn(topStockIds);
//
//        return popularStocks.stream()
//                .map(stock -> HomeDto.popularStockResponseDto.builder()
//                        .stockName(stock.getStockName())
//                        .predictCount(getPredictCount(topStockIds, stock.getId()))
//                        .build())
//                .collect(Collectors.toList());
//    }
//
//    private int getPredictCount(List<Long> stockIds, Long stockId) {
//        return Collections.frequency(stockIds, stockId);
//    }



}
