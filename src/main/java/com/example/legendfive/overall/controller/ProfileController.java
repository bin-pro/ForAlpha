package com.example.legendfive.overall.controller;


import com.example.legendfive.overall.Service.ProfileService;
import com.example.legendfive.overall.dto.ProfileDto;
import com.example.legendfive.overall.dto.ResponseDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/foralpha-service/profiles")
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/profile")
    public ResponseEntity<ResponseDto> getMyProfile(@RequestParam("user-uuid") UUID userUuid) {
        try {
            ProfileDto.MyProfileResponseDto myProfile = profileService.getMyProfile(userUuid);

            // 정상적인 응답 생성
            ResponseDto successResponse = ResponseDto.builder()
                    .payload(Collections.singletonMap("profile", myProfile))
                    .build();

            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        } catch (Exception e) {
            // 예외 발생 시 에러 응답
            ResponseDto errorResponse = ResponseDto.builder()
                    .error(e.getMessage())
                    .build();

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
