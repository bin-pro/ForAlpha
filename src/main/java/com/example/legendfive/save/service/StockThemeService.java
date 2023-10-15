package com.example.legendfive.save.service;

import com.example.legendfive.overall.Entity.Stock;
import com.example.legendfive.save.repository.StockApiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class StockThemeService {

    private final StockApiRepository stockApiRepository;
    @PostConstruct
    public void importDataFromCSV() {
        if (stockApiRepository.count() > 0) {
            // 데이터베이스에 이미 데이터가 존재하면 실행하지 않음
            return;
        }

        List<Stock> themesToSave = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                this.getClass().getResourceAsStream("/allStockTheme.csv"))) // CSV 파일 경로
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(","); // CSV 라인을 파싱
                Stock stock = Stock.builder()
                        .stockCode(data[0])
                        .stockName(data[1])
                        .themeName(data[2])
                        .stockUuid(UUID.randomUUID())
                        .build();

                themesToSave.add(stock); // 리스트에 추가
            }

            stockApiRepository.saveAll(themesToSave); // 데이터 일괄 저장
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
