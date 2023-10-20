package com.example.legendfive.overall.Service;

import com.example.legendfive.overall.Entity.User;
import com.example.legendfive.overall.dto.ProfileDto;
import com.example.legendfive.overall.repository.FriendRepository;
import com.example.legendfive.overall.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProfileService {
    private final UserRepository userRepository;
    private final FriendRepository friendsRepository;

    @Transactional(readOnly = true)
    public ProfileDto.MyProfileResponseDto getMyProfile(UUID userUuid) {
        // 사용자 정보 조회
        User user = userRepository.findByUserId(userUuid)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // 친구 수 조회
        long friendCount = friendsRepository.countFriendsByUser1Id(userUuid);



        // 프로필 DTO 생성
        ProfileDto.MyProfileResponseDto myProfileResponseDto = ProfileDto.MyProfileResponseDto.builder()
                .Nickname(user.getNickname())
                .friendCount((int) friendCount)
                .totalPoint(user.getUserPoint())
                .userInvestType(user.getUserInvestType())
                .build();

        return myProfileResponseDto;
    }
}
