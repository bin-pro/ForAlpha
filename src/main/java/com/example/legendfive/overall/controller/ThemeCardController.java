package com.example.legendfive.overall.controller;

import com.example.legendfive.overall.Service.ThemeCardService;
import com.example.legendfive.overall.dto.ResponseDto;
import com.example.legendfive.overall.dto.ThemeDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ThemeCardController {

    private final ThemeCardService themeCardService;
    private final ObjectMapper objectMapper;

    @GetMapping("/profile/theme-card")
    public ResponseEntity<ResponseDto> getThemeCardList(@RequestParam("user-uuid") UUID userUuid) {
        try {
            List<ThemeDto.ThemeCardListResponseDto> themeCardList = themeCardService.getThemeCardList(userUuid);

            Map<String, List<ThemeDto.ThemeCardListResponseDto>> payload = Collections.singletonMap("themeCardList", themeCardList);

            ResponseDto successResponse = ResponseDto.builder()
                    .payload(objectMapper.convertValue(payload, Map.class))
                    .build();

            return ResponseEntity.ok(successResponse);
        } catch (Exception e) {
            ResponseDto errorResponse = ResponseDto.builder()
                    .error(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
