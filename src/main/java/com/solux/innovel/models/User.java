// src/main/java/com/solux/innovel/models/User.java
package com.solux.innovel.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "profile")
    private String profile;

    @OneToMany(mappedBy = "writer")
    @JsonIgnore
    private List<Post> posts = new ArrayList<>();

    @Enumerated(EnumType.STRING) // 문자열로 저장되도록 함.
    @Column(name = "o_auth_provider")
    private OAuthProvider oAuthProvider; // 이 코드만 있을 시 db에서 자동으로 int로 받게 됨.

    @Builder
    public User(String email, String username, String profile, String socialId, OAuthProvider oAuthProvider) {
        this.email = email;
        this.username = username;
        this.profile = profile;
        this.oAuthProvider = oAuthProvider;
    }

    public void updateUsername(String username) {
        this.username = username;
    }

    public void updateProfile(String profile) {
        this.profile = profile;
    }
}