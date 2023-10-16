package com.example.legendfive.overall.repository;

import com.example.legendfive.overall.Entity.Quiz;
import com.example.legendfive.overall.Entity.QuizRecord;
import com.example.legendfive.overall.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRecordRepository extends JpaRepository<QuizRecord, Long> {
    List<QuizRecord> findByUserId(Long userId);

    //boolean existsByUserAndQuizAndCreatedAt(User user, Quiz quiz, LocalDate createdAt);

    //int countByUserAndCreatedAt(User user, LocalDate createdAt);

    int countByUserIdAndQuizDate(long id, LocalDate today);
}
