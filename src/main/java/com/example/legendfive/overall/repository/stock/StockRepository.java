package com.example.legendfive.overall.repository.stock;

import com.example.legendfive.overall.Entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long>, StockRepositoryCustom
{

    List<Stock> findByStockName(String brand);

    List<Stock> findByThemeName(String theme);
}
