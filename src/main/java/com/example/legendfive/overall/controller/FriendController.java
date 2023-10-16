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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
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

            UUID userId = addFriendRequestDto.getUserId();
            UUID friendId = addFriendRequestDto.getFriendId();

            FriendDto.AddFriendResponseDto addFriendResponseDto = friendService.addFriend(userId, friendId);

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
    public ResponseEntity<ResponseDto> getFriendList(@RequestParam("user-id") UUID userId) {
        try {

            FriendDto.GetFriendsResponseDto getFriendsResponseDto = friendService.getFriendList(userId);

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
            @RequestParam("nickname") String nickname,
            @RequestParam("user-id") UUID userId) {
        try {

            FriendDto.SearchUserRequestDto searchUserRequestDto = FriendDto.SearchUserRequestDto.builder()
                    .nickname(nickname)
                    .userId(userId)
                    .build();

            FriendDto.SearchUserResponseDto searchUserResponseDto = friendService.searchUsers(searchUserRequestDto);


            ResponseDto responseBody = ResponseDto.builder()
                    .payload(objectMapper.convertValue(searchUserResponseDto, Map.class))
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
