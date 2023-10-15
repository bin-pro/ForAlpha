package com.example.legendfive.overall.Service;

import com.example.legendfive.overall.Entity.User;
import com.example.legendfive.overall.dto.UserDto;
import com.example.legendfive.overall.dto.UserInfoDto;
import com.example.legendfive.overall.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaUserInfoConsumerServiceImpl implements KafkaUserInfoConsumerService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "user-create")
    @Transactional
    public void createUser(String payLoad, Acknowledgment acknowledgment) {
        log.info("received payload = '{}'", payLoad);

        UserInfoDto userInfoDto;

        try {
            userInfoDto = objectMapper.readValue(payLoad, UserInfoDto.class);
        } catch (Exception e) {
            log.error("Error converting to user info dto", e);
            return;
        }

        UUID userId = userInfoDto.getUserId();
        String nickname = userInfoDto.getNickname();

        User user = User.builder()
                .userId(userId)
                .nickname(nickname)
                .userPoint(100)
                .build();

        try {
            userRepository.save(user);
            acknowledgment.acknowledge();
        } catch (Exception e) {
            log.error("Error saving user nickname", e);
            return;
        }
    }

}
