package com.solux.innovel.OAuth.dto.Params;

import com.solux.innovel.models.OAuthProvider;
import org.springframework.util.MultiValueMap;

//OAuth 요청을 위한 파라미터 값을 지닌 인터페이스
public interface OAuthLoginParams {
    OAuthProvider oAuthProvider();
    MultiValueMap<String, String> makeBody();
}
