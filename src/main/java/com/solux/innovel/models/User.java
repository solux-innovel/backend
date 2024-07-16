package com.solux.innovel.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "profile")
    private String profile;

    @OneToMany(mappedBy = "writer")
    @JsonIgnore
    private List<Post> posts = new ArrayList<>();
}
