package com.example.legendfive.overall.repository.stock;

import com.example.legendfive.overall.Entity.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long>
{
    Page<Stock> findByStockNameContainingIgnoreCase(String brandName, Pageable pageable);
    Page<Stock> findByThemeNameContainingIgnoreCase(String themeName, Pageable pageable);

    List<Stock> findByIdIn(List<Long> ids);
}
