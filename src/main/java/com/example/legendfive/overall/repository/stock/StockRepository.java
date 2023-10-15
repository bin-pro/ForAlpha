package com.example.legendfive.overall.repository.stock;

import com.example.legendfive.overall.Entity.Stock;
import io.netty.util.internal.ObjectPool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StockRepository extends JpaRepository<Stock, Long>, StockRepositoryCustom
{

    List<Stock> findByStockName(String brand);
    List<Stock> findByThemeName(String theme);
    Optional<Stock> findByStockUuid(UUID stockUuid);
}
