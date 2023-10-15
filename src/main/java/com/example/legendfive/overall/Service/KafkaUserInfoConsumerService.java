package com.example.legendfive.overall.Service;

import org.springframework.kafka.support.Acknowledgment;

public interface KafkaUserInfoConsumerService {
    void createUploader(String payload, Acknowledgment acknowledgment);
    void updateUploader(String payload, Acknowledgment acknowledgment);
    void deleteUploader(String payload, Acknowledgment acknowledgment);
}
