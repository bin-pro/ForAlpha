package com.example.legendfive.oauth2;

import com.example.legendfive.dto.JwtDto;
import com.example.legendfive.entity.Role;
import com.example.legendfive.entity.User;
import com.example.legendfive.exception.UserErrorResult;
import com.example.legendfive.exception.UserException;
import com.example.legendfive.repository.UserRepository;
import com.example.legendfive.security.JwtTokenProvider;
import com.example.legendfive.service.VerificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service @RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final VerificationService verificationService;

    @Value("${spring.security.oauth2.redirect-uri}")
    private String REDIRECT_URI;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info(authentication.toString());

        CustomOAuth2User userPrincipal = (CustomOAuth2User) authentication.getPrincipal();
        log.info("userPrincipal {}", userPrincipal.getAttributes().toString());

        String userId = userPrincipal.getName();
        String email = userPrincipal.getEmail();

        log.info("userId {}", userId);
        log.info("email {}", email);

        //이메일로 회원 찾은 뒤 해당 회원의 password가 null이면 userSignInStatus를 false로 설정, null이 아니면 true로 설정
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(UserErrorResult.NOT_FOUND_USER));

        boolean userSignInStatus = user.getPassword() == null ? false : true;

        JwtDto.JwtRequestDto jwtRequestDto = JwtDto.JwtRequestDto
                .builder()
                .email(email)
                .userId(UUID.fromString(userId))
                .build();

        String accessToken = jwtTokenProvider.createAccessToken(jwtRequestDto);

        response.setStatus(HttpServletResponse.SC_OK);

        verificationService.saveCompletionCode(email, true);

        //클라이언트단에서 sign-in이 true면 로그인, false면 회원가입하도록
        response.sendRedirect(REDIRECT_URI + "/social-login?userId=" + userId + "&sign-in=" + String.valueOf(userSignInStatus) + "&email=" + email);
    }
}
