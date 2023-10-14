package com.example.legendfive.overall.Service;

import com.example.legendfive.overall.Entity.Friends;
import com.example.legendfive.overall.Entity.User;
import com.example.legendfive.overall.dto.FriendDto;
import com.example.legendfive.overall.repository.UserRepository;
import com.example.legendfive.overall.repository.FriendRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class FriendService {
    private final FriendRepository friendsRepository;
    private final UserRepository userRepository;

    public List<FriendDto.FriendListResponseDto> getFriendList(UUID userUuid) {
        // 사용자 정보 조회
        User user = userRepository.findByUserUuid(userUuid)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // Friends 엔터티에서 사용자의 ID로 친구 목록 조회 -> 없을때 빈 리스트 반환
        List<Friends> friendsList = friendsRepository.findAllByUser1Id(user.getId());

        // 친구 목록이 비어있을 경우 예외 던지기
        if (friendsList.isEmpty()) {
            throw new RuntimeException("친구 목록이 비어 있습니다.");
        }

        // 친구 목록을 FriendListResponseDto로 변환
        return friendsList.stream()
                .map(friends -> FriendDto.FriendListResponseDto.builder()
                        .friendNickname(getFriendNickname(user, friends))
                        .build())
                .collect(Collectors.toList());
    }

    private String getFriendNickname(User user, Friends friends) {
        // Friends 엔터티에서 user1Id와 user2Id 중 현재 사용자의 ID가 아닌 것을 찾아서 해당 사용자의 닉네임을 반환
        Long friendUserId = friends.getUser1Id().equals(user.getId()) ? friends.getUser2Id() : friends.getUser1Id();
        User friendUser = userRepository.findById(friendUserId)
                .orElseThrow(() -> new RuntimeException("친구를 찾을 수 없습니다."));
        return friendUser.getNickname();
    }

    public FriendDto.AddFriendResponseDto addFriend(UUID userUuid, String friendNickname) {
        // 사용자 정보 조회
        User user = userRepository.findByUserUuid(userUuid)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // 친구 정보 조회
        User friend = userRepository.findByNickname(friendNickname)
                .orElseThrow(() -> new RuntimeException("친구를 찾을 수 없습니다."));

        if (user.getId().equals(friend.getId())) {
            throw new RuntimeException("자기 자신을 친구로 추가할 수 없습니다.");
        }

        // 이미 친구인지 확인
        if (areAlreadyFriends(user.getId(), friend.getId())) {
            throw new RuntimeException("이미 친구입니다.");
        }

        // Friends 엔터티 생성
        Friends friends = Friends.builder()
                .user1Id(user.getId())
                .user2Id(friend.getId())
                .build();

        friendsRepository.save(friends);

        // Friends 엔터티 생성
        Friends friends2 = Friends.builder()
                .user1Id(friend.getId())
                .user2Id(user.getId())
                .build();

        friendsRepository.save(friends2);

        return FriendDto.AddFriendResponseDto.builder()
                .message("Complete to add friend")
                .build();
    }

    private boolean areAlreadyFriends(Long user1Id, Long user2Id) {
        // 이미 친구인지 확인
        return friendsRepository.existsByUser1IdAndUser2Id(user1Id, user2Id) ||
                friendsRepository.existsByUser1IdAndUser2Id(user2Id, user1Id);
    }


    public List<FriendDto.SearchFriendResponseDto> searchFriends(UUID userUuid, String friendNickname) {
        // 사용자 정보 조회
        User user = userRepository.findByUserUuid(userUuid)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // Friends 엔터티에서 사용자의 ID로 친구 목록 조회
        List<Friends> friendsList = friendsRepository.findAllByUser1Id(user.getId());

        // 친구 목록이 비어있을 경우 예외 던지기
        if (friendsList.isEmpty()) {
            throw new RuntimeException("친구 목록이 비어 있습니다.");
        }

        // 친구 목록을 FriendListResponseDto로 변환하며, friendNickname을 포함하는 친구들만 필터링
        List<FriendDto.SearchFriendResponseDto> searchFriendResponseDtoList = friendsList.stream()
                .map(friends -> FriendDto.SearchFriendResponseDto.builder()
                        .friendNickname(getFriendNickname(user, friends))
                        .build())
                .filter(dto -> dto.getFriendNickname().contains(friendNickname))
                .collect(Collectors.toList());

        return searchFriendResponseDtoList;
    }
}
