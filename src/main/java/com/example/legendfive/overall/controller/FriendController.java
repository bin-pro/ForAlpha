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
@Slf4j @RequestMapping("/friends")
public class FriendController {
    private final FriendService friendService;
    private final ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<ResponseDto> addFriend(@RequestBody FriendDto.AddFriendRequsetDto addFriendRequestDto) {
        try {

            UUID userUuid = addFriendRequestDto.getUserId();
            UUID friendUuid = addFriendRequestDto.getFriendId();

            FriendDto.AddFriendResponseDto addFriendResponseDto = friendService.addFriend(userUuid, friendUuid);

            ResponseDto responseDto = ResponseDto.builder()
                    .payload(objectMapper.convertValue(addFriendResponseDto, Map.class))
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch (RuntimeException e) {
            ResponseDto errorResponse = ResponseDto.builder()
                    .error(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseDto> getFriendList(@RequestParam("user-uuid") UUID userUuid) {
        try {

            FriendDto.GetFriendsResponseDto getFriendsResponseDto = friendService.getFriendList(userUuid);

            ResponseDto responseBody = ResponseDto.builder()
                    .payload(objectMapper.convertValue(getFriendsResponseDto, Map.class))
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        } catch (RuntimeException e) {
            ResponseDto errorResponse = ResponseDto.builder()
                    .error(e.getMessage())
                    .build();

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseDto> searchUsers(
            @RequestParam("friend-nickname") String friendNickname,
            @RequestParam("user-uuid") UUID userUuid) {
        try {
            List<FriendDto.SearchFriendResponseDto> searchResults = friendService.searchFriend(userUuid, friendNickname);
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
