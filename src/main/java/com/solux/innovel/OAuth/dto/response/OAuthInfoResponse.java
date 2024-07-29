package com.solux.innovel.OAuth.dto.response;

import com.solux.innovel.models.OAuthProvider;

public interface OAuthInfoResponse {
    String getEmail();
    String getUsername();
    OAuthProvider getOAuthProvider();
}
