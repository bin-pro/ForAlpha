package com.example.legendfive.repository;

import com.example.legendfive.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(final String email);
    boolean existsByEmail(final String email);
    Optional<User> findById(final UUID id);

    boolean existsById(final UUID id);
    boolean existsByNickname(final String nickname);

    @Query(value = "SELECT * FROM user WHERE email = ?1 AND password = ?2", nativeQuery = true)
    boolean existsByEmailAndPassword(final String email, final String password);
}
