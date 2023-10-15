package com.example.legendfive.overall.Service;

import org.springframework.kafka.support.Acknowledgment;

public interface KafkaUserInfoConsumerService {
    void createUser(String payload, Acknowledgment acknowledgment);
}
