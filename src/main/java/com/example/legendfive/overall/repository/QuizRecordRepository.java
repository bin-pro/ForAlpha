package com.example.legendfive.overall.repository;

import com.example.legendfive.overall.Entity.QuizRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
@Repository
public interface QuizRecordRepository extends JpaRepository<QuizRecord, Long> {
}
