package com.example.legendfive.overall.repository;

import com.example.legendfive.overall.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.userUuid = :userUuid")
    Optional<User> findByUserUuid(final UUID userUuid);
    Optional<User> findByNickname(final String nickname);

    @Query("SELECT u.id FROM User u WHERE u.userUuid = :userUuid")
    Optional<Long> findIdByUserUuid(UUID userUuid);
}
