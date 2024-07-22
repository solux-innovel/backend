package com.solux.innovel.OAuth.service;

import com.solux.innovel.OAuth.dto.token.AuthTokens;
import com.solux.innovel.OAuth.dto.AuthTokensGenerator;
import com.solux.innovel.OAuth.dto.response.OAuthInfoResponse;
import com.solux.innovel.OAuth.dto.Params.OAuthLoginParams;
import com.solux.innovel.models.User;
import com.solux.innovel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OAuthLoginService {
    private final UserRepository userRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;

    public AuthTokens login(OAuthLoginParams params) {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        Long memberId = findOrCreateMember(oAuthInfoResponse);
        return authTokensGenerator.generate(memberId);
    }

    private Long findOrCreateMember(OAuthInfoResponse oAuthInfoResponse) {
        return userRepository.findByEmail(oAuthInfoResponse.getEmail())
                .map(User::getId)
                .orElseGet(() -> newMember(oAuthInfoResponse));
    }

    private Long newMember(OAuthInfoResponse oAuthInfoResponse) {
        log.info("Creating new user with email: {} and username: {}", oAuthInfoResponse.getEmail(), oAuthInfoResponse.getUsername());
        User user = User.builder()
                .email(oAuthInfoResponse.getEmail() != null ? oAuthInfoResponse.getEmail() : "no-email@domain.com")
                .username(oAuthInfoResponse.getUsername())
                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .build();

        return userRepository.save(user).getId();
    }
}