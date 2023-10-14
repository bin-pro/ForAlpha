package com.example.legendfive.overall.controller;


import com.example.legendfive.overall.Service.FriendService;
import com.example.legendfive.overall.dto.FriendDto;
import com.example.legendfive.overall.dto.ResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FriendController {
    private final FriendService friendService;
    private final ObjectMapper objectMapper;

    @PostMapping("/feed/friends/search")
    public ResponseEntity<ResponseDto> addFriend(@RequestBody FriendDto.AddFriendRequsetDto addFriendRequestDto) {
        try {
            UUID userUuid = addFriendRequestDto.getUserUuId();
            String friendNickname = addFriendRequestDto.getFriendNickname();

            FriendDto.AddFriendResponseDto responseDto = friendService.addFriend(userUuid, friendNickname);

            ResponseDto responseBody = ResponseDto.builder()
                    .payload(objectMapper.convertValue(responseDto, Map.class))
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        } catch (RuntimeException e) {
            ResponseDto errorResponse = ResponseDto.builder()
                    .error(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping("/my_porfile/friends")
    public ResponseEntity<ResponseDto> getFriendList(@RequestParam("user-uuid") UUID userUuid) {
        try {
            List<FriendDto.FriendListResponseDto> friendList = friendService.getFriendList(userUuid);

            Map<String, Object> payload = new HashMap<>();
            payload.put("friendList", friendList);

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

    @GetMapping("/feed/friends/search")
    public ResponseEntity<ResponseDto> searchFriends(
            @RequestParam("friend-nickname") String friendNickname,
            @RequestParam("user-uuid") UUID userUuid) {
        try {
            List<FriendDto.SearchFriendResponseDto> searchResults = friendService.searchFriends(userUuid, friendNickname);
//            ResponseDto successResponse = ResponseDto.builder()
//                    .payload(searchResults)
//                    .build();

            Map<String, Object> payload = new HashMap<>();
            payload.put("searchResults", searchResults);

            ResponseDto responseBody = ResponseDto.builder()
                    .payload(payload)
                    .build();

            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (RuntimeException e) {
            ResponseDto errorResponse = ResponseDto.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }



}
