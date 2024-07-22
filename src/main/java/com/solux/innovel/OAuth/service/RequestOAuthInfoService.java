package com.solux.innovel.OAuth.service;

import com.solux.innovel.OAuth.dto.response.OAuthInfoResponse;
import com.solux.innovel.OAuth.dto.Params.OAuthLoginParams;
import com.solux.innovel.OAuth.dto.client.OAuthApiClient;
import com.solux.innovel.models.OAuthProvider;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
// OAuthApiClient 를 사용하는 Service 클래스
@Component
public class RequestOAuthInfoService {
    private final Map<OAuthProvider, OAuthApiClient> clients;
    public RequestOAuthInfoService(List<OAuthApiClient> clients){
        this.clients=clients.stream().collect(
                Collectors.toUnmodifiableMap(OAuthApiClient::oAuthProvider, Function.identity())
        );
    }
    public OAuthInfoResponse request(OAuthLoginParams params) {
        OAuthApiClient client = clients.get(params.oAuthProvider());
        String accessToken = client.requestAccessToken(params);
        return client.requestOauthInfo(accessToken);
    }
}
