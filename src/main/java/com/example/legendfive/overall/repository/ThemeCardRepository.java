package com.example.legendfive.overall.repository;

import com.example.legendfive.overall.Entity.Friend;
import com.example.legendfive.overall.Entity.ThemeCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ThemeCardRepository extends JpaRepository<ThemeCard, Long> {

    List<ThemeCard> findByUserId(Long userId);

    @Query("SELECT t.themeName, COUNT(t.themeName) FROM ThemeCard t WHERE t.user.id = :userId GROUP BY t.themeName")
    List<Object[]> countByThemeNameAndUserId(Long userId);
}
