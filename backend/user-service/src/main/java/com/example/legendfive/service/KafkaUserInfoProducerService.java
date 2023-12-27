package com.example.legendfive.service;

import com.example.legendfive.dto.UserDto;

public interface KafkaUserInfoProducerService {
    void createUser(UserDto.UserInfoDto userInfoDto);
}
