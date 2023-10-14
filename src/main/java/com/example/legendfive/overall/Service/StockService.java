package com.example.legendfive.overall.Service;

import com.example.legendfive.overall.Entity.Stock;
import com.example.legendfive.overall.dto.StockDto;
import com.example.legendfive.overall.repository.stock.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;

    public Page<StockDto.SearchStockBrandResponseDto> searchStockByBrandName(String brandName, Pageable pageable) {
        Page<Stock> searchResults = stockRepository.findByStockNameContainingIgnoreCase(brandName, pageable);

        // Page를 DTO로 변환
        return searchResults.map(stockEntity -> StockDto.SearchStockBrandResponseDto.builder()
                .StockName(stockEntity.getStockName())
                .build());
    }

    public Page<StockDto.SearchStockBrandResponseDto> searchStockByThemeName(String themeName, Pageable pageable) {
        Page<Stock> searchResults = stockRepository.findByThemeNameContainingIgnoreCase(themeName, pageable);

        // Page를 DTO로 변환
        return searchResults.map(stockEntity -> StockDto.SearchStockBrandResponseDto.builder()
                .StockName(stockEntity.getStockName())
                .build());
    }
}
