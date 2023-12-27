package com.example.legendfive.service;

import com.example.legendfive.dto.JwtDto;
import com.example.legendfive.dto.UserDto;
import com.example.legendfive.entity.Role;
import com.example.legendfive.entity.User;
import com.example.legendfive.exception.UserErrorResult;
import com.example.legendfive.exception.UserException;
import com.example.legendfive.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.legendfive.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final KafkaUserInfoProducerService kafkaUserInfoProducerService;

    public UserDto.SignInResponseDto signIn(UserDto.SignInRequestDto signInRequestDto) {

        //social login: provider
        userRepository.save(User.builder()
                .userId(UUID.randomUUID())
                .email(signInRequestDto.getEmail())
                .password(passwordEncoder.encode(signInRequestDto.getPassword()))
                .nickname(signInRequestDto.getNickname())
                .role(Role.valueOf("USER"))
                .build());

        Optional<User> user = userRepository.findByEmail(signInRequestDto.getEmail());

        UserDto.UserInfoDto userInfoDto = UserDto.UserInfoDto.builder()
                .userId(user.get().getUserId())
                .nickname(signInRequestDto.getNickname())
                .build();

        kafkaUserInfoProducerService.createUser(userInfoDto);

        log.info("회원가입 성공");
        return UserDto.SignInResponseDto.builder()
                .userId(user.get().getUserId())
                .createdAt(user.get().getCreatedAt())
                .build();

    }
    @Override
    public UserDto.SocialSignInResponseDto socialSignIn(UserDto.SignInRequestDto socialSignInRequestDto) {

        User user = userRepository.findByEmail(socialSignInRequestDto.getEmail())
                .orElseThrow(() -> new UserException(UserErrorResult.NOT_FOUND_USER));

        user = user.toBuilder()
                .nickname(socialSignInRequestDto.getNickname())
                .password(passwordEncoder.encode(socialSignInRequestDto.getPassword()))
                .build();

        log.info("user: {}", user);
        log.info("user.getUserId(): {}", user.getUserId());
        log.info("user.getEmail(): {}", user.getEmail());
        log.info("user.getPassword(): {}", user.getPassword());
        log.info("user.getNickname(): {}", user.getNickname());

        userRepository.save(user);

        UserDto.UserInfoDto userInfoDto = UserDto.UserInfoDto.builder()
                .userId(user.getUserId())
                .nickname(socialSignInRequestDto.getNickname())
                .build();

        kafkaUserInfoProducerService.createUser(userInfoDto);

        log.info("소셜 로그인 후 회원가입 성공");
        return UserDto.SocialSignInResponseDto.builder()
                .userId(user.getUserId())
                .createdAt(user.getCreatedAt())
                .build();

    }


    public UserDto.LoginResponseDto login(UserDto.LoginRequestDto loginRequestDto) {

        User user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new UserException(UserErrorResult.NOT_FOUND_USER));

        JwtDto.JwtRequestDto jwtRequestDto = JwtDto.JwtRequestDto.builder()
                .email(loginRequestDto.getEmail())
                .userId(user.getUserId())
                .build();

        String jwt = jwtTokenProvider.createAccessToken(jwtRequestDto);

        return UserDto.LoginResponseDto.builder()
                .userId(user.getUserId())
                .createdAt(LocalDateTime.now())
                .accessToken(jwt)
                .build();
    }

    public boolean userExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserDto.UpdatePasswordResponseDto updatePassword(UserDto.UpdatePasswordRequestDto updatePasswordRequestDto) {

        User originalUser = userRepository.findByEmail(updatePasswordRequestDto.getEmail())
                .orElseThrow(() -> new UserException(UserErrorResult.NOT_FOUND_USER));

        User updatedUser = originalUser.toBuilder()
                .password(passwordEncoder.encode(updatePasswordRequestDto.getNewPassword()))
                .build();

        userRepository.save(updatedUser);

        return UserDto.UpdatePasswordResponseDto.builder()
                .userId(updatedUser.getUserId())
                .updatedAt(updatedUser.getUpdatedAt())
                .build();
    }

    @Override
    public boolean userExistsByNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    @Override
    public boolean userExistsByEmailAndPassword(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (passwordEncoder.matches(password, user.getPassword())) {
                return true; // 인증 성공
            }
        }

        return false; // 인증 실패
    }

    @Override
    public boolean userHasProvider(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (user.getProvider() != null) {
                return true;
            }
        }
        return false;
    }

}
