package com.example.legendfive.overall.repository;

import com.example.legendfive.overall.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserUuid(final UUID userUuid);
}
