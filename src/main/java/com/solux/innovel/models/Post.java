package com.solux.innovel.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.solux.innovel.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동증가 추가. AUTO INCREMENT 사용.
    @Column(name = "post_id", nullable = false) // false 설정 추가. NOT NULL 제약 조건을 명시.
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User writer;

    private String title;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String content;

    private String thumbnail;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    public void updatePost(Post postDetails) {
        this.title = postDetails.getTitle();
        this.content = postDetails.getContent();
        this.thumbnail = postDetails.getThumbnail();
        this.genre = postDetails.getGenre();
    }
}
