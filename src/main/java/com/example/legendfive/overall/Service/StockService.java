package com.example.legendfive.overall.Service;

import com.example.legendfive.overall.Entity.Stock;
import com.example.legendfive.overall.repository.stock.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;

    public List<Stock> findByStocks(String brand) {

        return stockRepository.findByStockName(brand);
    }

    public List<Stock> findBybrands(String theme) {
        return stockRepository.findByThemeName(theme);
    }
}
