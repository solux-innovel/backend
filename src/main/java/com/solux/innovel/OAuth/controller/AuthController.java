package com.solux.innovel.OAuth.controller;

import com.solux.innovel.OAuth.dto.token.AuthTokens;
import com.solux.innovel.OAuth.dto.Params.KakaoLoginParams;
import com.solux.innovel.OAuth.dto.Params.NaverLoginParams;
import com.solux.innovel.OAuth.service.OAuthLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {
    private final OAuthLoginService oAuthLoginService;

    @PostMapping("/kakao")
    public ResponseEntity<AuthTokens> loginKakao(@RequestBody KakaoLoginParams params) {
        try {
            return ResponseEntity.ok(oAuthLoginService.login(params));
        } catch (Exception e) {
            log.error("Error during Kakao login", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/naver")
    public ResponseEntity<AuthTokens> loginNaver(@RequestBody NaverLoginParams params) {
        try {
            return ResponseEntity.ok(oAuthLoginService.login(params));
        } catch (Exception e) {
            log.error("Error during Naver login", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}