package com.example.legendfive.overall.Service;


import com.amazonaws.services.s3.AmazonS3;
import com.example.legendfive.overall.Entity.Stock;
import com.example.legendfive.overall.dto.HomeDto;
import com.example.legendfive.overall.repository.PredictionRecordRepository;
import com.example.legendfive.overall.repository.stock.StockRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class PopularService {

    private final PredictionRecordRepository predictionRecordRepository;
    private final StockRepository stockRepository;
    private final StockService stockService;
    private final AmazonS3 amazonS3;

    private static final String S3_BUCKET_NAME = "api-test01";
    private static final String S3_FILE_PATH = "todayStock/";

    //가격 추가
    public List<HomeDto.popularStockResponseDto> getPopularStocks() {
        List<Object[]> stockIdAndPredictCountList = predictionRecordRepository.findTop30StockIdsAndPredictCountOrderByCountDesc();

        return stockIdAndPredictCountList.stream()
                .map(result -> {
                    Long stockId = (Long) result[0];
                    Long predictCount = (Long) result[1];

                    Stock stock = stockRepository.findById(stockId).orElse(null);
                    String stockPrice= "";

                    if (stock != null) {
                        try {
                            stockPrice = stockService.getStockFromS33(stock.getStockCode()).getStock_price();
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    //stock 주식 가격 가져오는 로직

                    return HomeDto.popularStockResponseDto.builder()
                            .stockName(stock != null ? stock.getStockName() : null)
                            .predictCount(predictCount.intValue())
                            .stockPrice(stockPrice)
                            .build();
                })
                .collect(Collectors.toList());
    }
}
