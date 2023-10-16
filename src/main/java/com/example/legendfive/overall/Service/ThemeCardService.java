package com.example.legendfive.overall.Service;


import com.example.legendfive.overall.Entity.ThemeCard;
import com.example.legendfive.overall.Entity.User;
import com.example.legendfive.overall.dto.ThemeDto;
import com.example.legendfive.overall.repository.ThemeCardRepository;
import com.example.legendfive.overall.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ThemeCardService {
    private final ThemeCardRepository themeCardRepository;
    private final UserRepository userRepository;

    public List<ThemeDto.ThemeCardListResponseDto> getThemeCardList(UUID userUuid) {
        User user = userRepository.findByUserId(userUuid)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        List<ThemeCard> themeCards = themeCardRepository.findByUserId(user.getId());
        return themeCards.stream()
                .map(themeCard -> ThemeDto.ThemeCardListResponseDto.builder()
                        .themeName(themeCard.getThemeName())
                        .createdAt(themeCard.getCreatedAt())
                        .userNickname(user.getNickname())
                        .build())
                .collect(Collectors.toList());
    }

}
