package com.example.legendfive.overall.Service;

import com.example.legendfive.overall.Entity.User;
import com.example.legendfive.overall.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> getUserByUserId(UUID userid) {
        Optional<User> user = Optional.of(userRepository.findByUserUuid(userid).orElseThrow());
        return user;
    }
}