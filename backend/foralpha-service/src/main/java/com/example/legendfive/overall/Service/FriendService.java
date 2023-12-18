package com.example.legendfive.overall.Service;

import com.example.legendfive.overall.Entity.Friend;
import com.example.legendfive.overall.Entity.User;
import com.example.legendfive.overall.dto.FriendDto;
import com.example.legendfive.overall.repository.UserRepository;
import com.example.legendfive.overall.repository.FriendRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.amazonaws.services.ec2.model.FindingsFound.True;

@Slf4j
@Service
@AllArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly=true)
    public FriendDto.GetFriendsResponseDto getFriendList(UUID userUuid) {
        // 사용자 정보 조회
        User user = userRepository.findByUserId(userUuid)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        if(user.getUserId().equals(user.getNickname())){
            throw new RuntimeException("자기자신입니다.");
        }

        // Friends 엔터티에서 사용자의 ID로 친구 목록 조회 -> 없을때 빈 리스트 반환
        List<Friend> friendsList = friendRepository.findAllByUser1Id(user.getUserId());

        // 친구 목록이 비어있을 경우 예외 던지기
        if (friendsList.isEmpty()) {
            throw new RuntimeException("친구 목록이 비어 있습니다.");
        }
        //{
        //  "payload": {
        //    "user_id": "13cdf450-8778-42d6-8a34-ce91a0bbb7e7",
        //    "friend_list": [
        //      {
        //        "friend_id": "23cdf450-8778-42d6-8a34-ce91a0bbb7e7",
        //        "friend_nickname": "u2"
        //      },
        //      {
        //        "friend_id": "33cdf450-8778-42d6-8a34-ce91a0bbb7e7",
        //        "friend_nickname": "u3"
        //      },
        //      {
        //        "friend_id": "43cdf450-8778-42d6-8a34-ce91a0bbb7e7",
        //        "friend_nickname": "u4"
        //      }
        //    ]
        //  }
        //}
        FriendDto.GetFriendsResponseDto getFriendsResponseDto = FriendDto.GetFriendsResponseDto.builder()
                .userId(user.getUserId())
                .friendList(friendsList.stream()
                        .map(friend -> {
                            return Map.of(
                                    "friend_nickname", getFriendNickname(user, friend),
                                    "friend_id", friend.getUser2Id().toString()
                            );
                        })
                        .collect(Collectors.toList()))
                .build();

        return getFriendsResponseDto;
    }
    @Transactional(readOnly=true)
    private String getFriendNickname(User user, Friend friend) {
        // Friends 엔터티에서 user1Id와 user2Id 중 현재 사용자의 ID가 아닌 것을 찾아서 해당 사용자의 닉네임을 반환
        UUID friendUserId = friend.getUser1Id().equals(user.getUserId()) ? friend.getUser2Id() : friend.getUser1Id();

        User friendUser = userRepository.findByUserId(friendUserId)
                .orElseThrow(() -> new RuntimeException("친구를 찾을 수 없습니다."));
        return friendUser.getNickname();
    }

    @Transactional
    public FriendDto.AddFriendResponseDto addFriend(UUID userUuid, UUID friendUuid) {
        // 사용자 정보 조회
        User user = userRepository.findByUserId(userUuid)
                .orElseThrow(() -> new RuntimeException("사용자를  찾을 수 없습니다."));

        User friend = userRepository.findByUserId(friendUuid)
                .orElseThrow(() -> new RuntimeException("친구를 찾을 수 없습니다."));

        if (user.getId().equals(friend.getId())) {
            throw new RuntimeException("자기 자신을 친구로 추가할 수 없습니다.");
        }

        // 이미 친구인지 확인
        if (areAlreadyFriends(userUuid, friendUuid)) {
            throw new RuntimeException("이미 친구입니다.");
        }

        Friend newFriend = Friend.builder()
                .user1Id(user.getUserId())
                .user2Id(friend.getUserId())
                .user(user)
                .build();

        Friend newFriend2 = Friend.builder()
                .user1Id(friend.getUserId())
                .user2Id(user.getUserId())
                .user(friend)
                .build();

        friendRepository.save(newFriend);
        friendRepository.save(newFriend2);

        return FriendDto.AddFriendResponseDto.builder()
                .message("Complete to add friend")
                .build();
    }

    @Transactional(readOnly=true)
    private boolean areAlreadyFriends(UUID user1Id, UUID user2Id) {
        // 이미 친구인지 확인
        return friendRepository.existsByUser1IdAndUser2Id(user1Id, user2Id);
    }

    public FriendDto.SearchUserResponseDto searchUsers(FriendDto.SearchUserRequestDto searchUserRequestDto) {
        UUID userId = searchUserRequestDto.getUserId();
        String nickname = searchUserRequestDto.getNickname();

        // 사용자 정보 조회
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        //without user id
        FriendDto.SearchUserResponseDto searchUserResponseDto = FriendDto.SearchUserResponseDto.builder()
                .userId(user.getUserId())
                .userList(userRepository.findByNickname(nickname, userId).stream()
                        .map(user1 -> {
                            return Map.of(
                                    "user_nickname", user1.getNickname(),
                                    "user_id", user1.getUserId().toString()
                            );
                        })
                        .collect(Collectors.toList()))
                .build();

        return searchUserResponseDto;
    }
}
