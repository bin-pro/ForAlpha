package com.example.legendfive.overall.repository;

import com.example.legendfive.overall.Entity.QuizRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRecordRepository extends JpaRepository<QuizRecord, Long> {
    List<QuizRecord> findByUserId(Long userId);
}
