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

    @Column(name = "username", unique = false, nullable = false)
    private String username;

    @Column(name = "email", unique = false, nullable = false)
    private String email;

    @Column(name = "social_id", unique = true, nullable = false)
    private String socialId;

    @Column(name = "profile")
    private String profile;

    @OneToMany(mappedBy = "writer")
    @JsonIgnore
    private List<Post> posts = new ArrayList<>();

    @Builder
    public User(String email, String username, String profile, String socialId) {
        this.email = email;
        this.username = username;
        this.profile = profile;
        this.socialId = socialId;
    }

    public void updateUsername(String username) {
        this.username = username;
    }

    public void updateProfile(String profile) {
        this.profile = profile;
    }
}