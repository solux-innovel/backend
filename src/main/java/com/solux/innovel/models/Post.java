package com.solux.innovel.models;

import com.solux.innovel.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동증가 추가. AUTO INCREMENT 사용.
    @Column(name = "post_id", nullable = false) // false 설정 추가. NOT NULL 제약 조건을 명시.
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User writer;

    private String title;

    private String content;

    private String summary;

    private String thumbnail;

    @Enumerated(EnumType.STRING)
    private Genre genre;
}
