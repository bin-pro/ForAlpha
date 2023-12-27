package com.example.legendfive.service;

import com.example.legendfive.dto.ResponseDto;
import com.example.legendfive.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    UserDto.SignInResponseDto signIn(UserDto.SignInRequestDto signInRequestDto);

    UserDto.SocialSignInResponseDto socialSignIn(UserDto.SignInRequestDto socialSignInRequestDto);
    boolean userExistsByEmail(String email);
    UserDto.UpdatePasswordResponseDto updatePassword(UserDto.UpdatePasswordRequestDto updatePasswordRequestDto);
    UserDto.LoginResponseDto login(UserDto.LoginRequestDto loginRequestDto);
    boolean userExistsByNickname(String nickname);
    boolean userExistsByEmailAndPassword(String email, String password);
    boolean userHasProvider(String email);
}
