package com.example.legendfive.overall.repository;

import com.example.legendfive.overall.Entity.PredictionRecord;
import com.example.legendfive.overall.Entity.Stock;
import com.example.legendfive.overall.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PredictionRecordRepository extends JpaRepository<PredictionRecord, Long> {
    Optional<PredictionRecord> findByStockAndUser(Stock stock, User user);

    List<PredictionRecord> findByUser(User user);

    @Query("SELECT p.stock.id " +
            "FROM PredictionRecord p " +
            "JOIN p.stock s " +
            "GROUP BY p.stock.id, s.stockCode " +
            "ORDER BY COUNT(p.stockCode) DESC ")
    List<Long> findStockIdsOrderByCountDesc();
}
