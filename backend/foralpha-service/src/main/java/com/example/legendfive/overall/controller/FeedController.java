package com.example.legendfive.overall.controller;


import com.example.legendfive.overall.Service.FeedService;
import com.example.legendfive.overall.dto.FeedDto;
import com.example.legendfive.overall.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/foralpha-service/feed")
public class FeedController {

    private final FeedService feedService;
    @GetMapping
    public ResponseEntity<ResponseDto> getFriendPredictions(@RequestParam("user-uuid") UUID userUuid) {
        try {
            List<FeedDto.FriendFeedListResponseDto> friendPredictions = feedService.getFriendPredictions(userUuid);
            Map<String, Object> payload = new HashMap<>();
            payload.put("friendPredictions", friendPredictions);

            ResponseDto responseBody = ResponseDto.builder()
                    .payload(payload)
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        } catch (RuntimeException e) {
            ResponseDto errorResponse = ResponseDto.builder()
                    .error(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

}
