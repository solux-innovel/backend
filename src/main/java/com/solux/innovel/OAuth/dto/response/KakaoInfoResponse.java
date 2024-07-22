package com.solux.innovel.OAuth.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.solux.innovel.models.OAuthProvider;
import lombok.Getter;
// https://kapi.kakao.com/v2/user/me 요청 결과값
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoInfoResponse implements OAuthInfoResponse{
    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class KakaoAccount{
        private KakaoProfile profile;
        private String email;
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class KakaoProfile {
        private String nickname;
    }
    @Override
    public String getEmail(){
        return kakaoAccount.email;
    }

    @Override
    public String getUsername(){
        return kakaoAccount.profile.nickname;
    }
    @Override
    public OAuthProvider getOAuthProvider(){
        return OAuthProvider.KAKAO;
    }
}
