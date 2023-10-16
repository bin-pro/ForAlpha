package com.example.legendfive.overall.repository;

import com.example.legendfive.overall.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.userId = :userUuid")
    Optional<User> findByUserUuid(final UUID userUuid);

    @Query("SELECT u FROM User u WHERE u.nickname LIKE %:nickname% AND u.userId <> :userId")
    List<User> findByNickname(final String nickname, final UUID userId);

    @Query("SELECT u.id FROM User u WHERE u.userId= :userUuid")
    Optional<Long> findIdByUserUuid(UUID userUuid);

    Optional<Long> findIdByUserId(UUID userId);
}
