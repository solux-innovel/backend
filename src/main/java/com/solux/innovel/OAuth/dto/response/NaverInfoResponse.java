package com.solux.innovel.OAuth.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.solux.innovel.models.OAuthProvider;
import lombok.Getter;
// https://openapi.naver.com/v1/nid/me 요청 결과값
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaverInfoResponse implements OAuthInfoResponse{
    @JsonProperty("response")
    private Response response;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Response{
        private String nickname;
        private String email;
    }
    @Override
    public String getEmail(){
        return response.email;
    }

    @Override
    public String getUsername(){
        return response.nickname;
    }
    @Override
    public OAuthProvider getOAuthProvider(){
        return OAuthProvider.NAVER;
    }
}
