package com.example.legendfive.overall.repository;

import com.example.legendfive.overall.Entity.PredictionRecord;
import com.example.legendfive.overall.Entity.Stock;
import com.example.legendfive.overall.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PredictionRecordRepository extends JpaRepository<PredictionRecord, Long> {
    Optional<PredictionRecord> findByStockAndUser(Stock stock, User user);

    List<PredictionRecord> findByUser(User user);

    Optional<PredictionRecord> findByStockAndUserAndCreatedAt(Stock stock, User user, LocalDate now);
}
