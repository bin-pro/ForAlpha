package com.example.legendfive.overall.repository;

import com.example.legendfive.overall.Entity.Friends;
import com.example.legendfive.overall.Entity.ThemeCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ThemeCardRepository extends JpaRepository<ThemeCard, Long> {

    List<ThemeCard> findByUserId(Long userId);
}
