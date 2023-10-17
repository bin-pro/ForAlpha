package com.example.legendfive.overall.repository.stock;

import com.example.legendfive.overall.Entity.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface StockRepository extends JpaRepository<Stock, Long>
{
    Page<Stock> findByStockNameContainingIgnoreCase(String brandName, Pageable pageable);

    @Query("SELECT s FROM Stock s WHERE s.themeName LIKE %:themeName%")
    Page<Stock> findByThemeName(String themeName, Pageable pageable);
    Optional<Stock> findByStockCode(String stockCode);

    Optional<Stock> findByStockName(String stockName);
}
