package com.solux.innovel.userUpdate;

import lombok.Data;

@Data
public class ProfileUpdateRequest {
    private String username;
    private String profileImageUrl;
}