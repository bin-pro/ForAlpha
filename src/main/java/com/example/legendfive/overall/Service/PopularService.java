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
public class PopularService {
    private final PredictionRecordRepository predictionRecordRepository;
    private final StockRepository stockRepository;

    public List<HomeDto.popularStockResponseDto> getPopularStocks() {
        List<Object[]> stockIdAndPredictCountList = predictionRecordRepository.findTop30StockIdsAndPredictCountOrderByCountDesc();

        return stockIdAndPredictCountList.stream()
                .map(result -> {
                    Long stockId = (Long) result[0];
                    Long predictCount = (Long) result[1];

                    Stock stock = stockRepository.findById(stockId).orElse(null);

                    return HomeDto.popularStockResponseDto.builder()
                            .stockName(stock != null ? stock.getStockName() : null)
                            .predictCount(predictCount.intValue())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
