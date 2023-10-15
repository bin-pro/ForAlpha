package com.example.legendfive.overall.Service;

import com.example.legendfive.overall.Entity.Friend;
import com.example.legendfive.overall.Entity.User;
import com.example.legendfive.overall.dto.FriendDto;
import com.example.legendfive.overall.repository.UserRepository;
import com.example.legendfive.overall.repository.FriendRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    public FriendDto.GetFriendsResponseDto getFriendList(UUID userUuid) {
        // 사용자 정보 조회
        User user = userRepository.findByUserUuid(userUuid)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // Friends 엔터티에서 사용자의 ID로 친구 목록 조회 -> 없을때 빈 리스트 반환
        List<Friend> friendsList = friendRepository.findAllByUser1Id(user.getUserUuid());

        // 친구 목록이 비어있을 경우 예외 던지기
        if (friendsList.isEmpty()) {
            throw new RuntimeException("친구 목록이 비어 있습니다.");
        }
        //반환형태
        //    {
        //    "payload": {
        //        "user_uuid": "13cdf450-8778-42d6-8a34-ce91a0bbb7e7",
        //        "friend_list": [
        //            {
        //                "friendUuid": "23cdf450-8778-42d6-8a34-ce91a0bbb7e7",
        //                "friendNickname": "u2"
        //            },
        //            {
        //                "friendUuid": "33cdf450-8778-42d6-8a34-ce91a0bbb7e7",
        //                "friendNickname": "u3"
        //            }
        //        ]
        //    }
        //}
        FriendDto.GetFriendsResponseDto getFriendsResponseDto = FriendDto.GetFriendsResponseDto.builder()
                .userUuid(user.getUserUuid())
                .friendList(friendsList.stream()
                        .map(friend -> {
                            return Map.of(
                                    "friendNickname", getFriendNickname(user, friend),
                                    "friendUuid", friend.getUser2Id().toString()
                            );
                        })
                        .collect(Collectors.toList()))
                .build();

        return getFriendsResponseDto;
    }

    private String getFriendNickname(User user, Friend friend) {
        // Friends 엔터티에서 user1Id와 user2Id 중 현재 사용자의 ID가 아닌 것을 찾아서 해당 사용자의 닉네임을 반환
        UUID friendUserId = friend.getUser1Id().equals(user.getUserUuid()) ? friend.getUser2Id() : friend.getUser1Id();

        User friendUser = userRepository.findByUserUuid(friendUserId)
                .orElseThrow(() -> new RuntimeException("친구를 찾을 수 없습니다."));
        return friendUser.getNickname();
    }

    public FriendDto.AddFriendResponseDto addFriend(UUID userUuid, UUID friendUuid) {
        // 사용자 정보 조회
        User user = userRepository.findByUserUuid(userUuid)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        User friend = userRepository.findByUserUuid(friendUuid)
                .orElseThrow(() -> new RuntimeException("친구를 찾을 수 없습니다."));

        if (user.getId().equals(friend.getId())) {
            throw new RuntimeException("자기 자신을 친구로 추가할 수 없습니다.");
        }

        // 이미 친구인지 확인
        if (areAlreadyFriends(userUuid, friendUuid)) {
            throw new RuntimeException("이미 친구입니다.");
        }

        // Friends 엔터티 생성
        Friend newFriend = Friend.builder()
                .user1Id(user.getUserUuid())
                .user2Id(friend.getUserUuid())
                .build();

        friendRepository.save(newFriend);

        // Friend 엔터티 생성
        Friend newFriend2 = Friend.builder()
                .user1Id(friend.getUserUuid())
                .user2Id(user.getUserUuid())
                .build();

        friendRepository.save(newFriend2);

        return FriendDto.AddFriendResponseDto.builder()
                .message("Complete to add friend")
                .build();
    }

    private boolean areAlreadyFriends(UUID user1Id, UUID user2Id) {
        // 이미 친구인지 확인
        return friendRepository.existsByUser1IdAndUser2Id(user1Id, user2Id);
    }


    public List<FriendDto.SearchFriendResponseDto> searchFriend(UUID userUuid, String friendNickname) {
        // 사용자 정보 조회
        User user = userRepository.findByUserUuid(userUuid)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // Friend 엔터티에서 사용자의 ID로 친구 목록 조회
        List<Friend> FriendList = friendRepository.findAllByUser1Id(user.getUserUuid());

        // 친구 목록이 비어있을 경우 예외 던지기
        if (FriendList.isEmpty()) {
            throw new RuntimeException("친구 목록이 비어 있습니다.");
        }

        // 친구 목록을 FriendListResponseDto로 변환하며, friendNickname을 포함하는 친구들만 필터링
        List<FriendDto.SearchFriendResponseDto> searchFriendResponseDtoList = FriendList.stream()
                .map(Friend -> FriendDto.SearchFriendResponseDto.builder()
                        .friendNickname(getFriendNickname(user, Friend))
                        .build())
                .filter(dto -> dto.getFriendNickname().contains(friendNickname))
                .collect(Collectors.toList());

        return searchFriendResponseDtoList;
    }
}
