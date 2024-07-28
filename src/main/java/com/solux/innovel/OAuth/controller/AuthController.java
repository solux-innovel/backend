package com.solux.innovel.OAuth.controller;

import com.solux.innovel.OAuth.dto.Params.KakaoLoginParams;
import com.solux.innovel.OAuth.dto.Params.NaverLoginParams;
import com.solux.innovel.OAuth.dto.token.AuthTokens;
import com.solux.innovel.OAuth.service.OAuthLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {
    private final OAuthLoginService oAuthLoginService;

    @PostMapping("/kakao")
    public ResponseEntity<AuthTokens> loginKakao(@RequestBody KakaoLoginParams params) {
        try {
            AuthTokens authTokens = oAuthLoginService.login(params);
            // JWT 토큰을 로그에 출력
            log.info("Generated JWT Access Token: {}", authTokens.getAccessToken());
            log.info("Generated JWT Refresh Token: {}", authTokens.getRefreshToken());
            return ResponseEntity.ok(authTokens); //프론트에게 jwt코드 보내기
        } catch (Exception e) {
            log.error("Error during Kakao login", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/naver")
    public ResponseEntity<AuthTokens> loginNaver(@RequestBody NaverLoginParams params) {
        try {
            AuthTokens authTokens = oAuthLoginService.login(params);
            // JWT 토큰을 로그에 출력
            log.info("Generated JWT Access Token: {}", authTokens.getAccessToken());
            log.info("Generated JWT Refresh Token: {}", authTokens.getRefreshToken());

            return ResponseEntity.ok(authTokens);
        } catch (Exception e) {
            log.error("Error during Naver login", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
