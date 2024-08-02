package com.solux.innovel.mypage.mypost.dto;

import com.solux.innovel.models.Post;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MyPostResponseDTO {
    private List<PostDTO> posts;
    private int totalPages;
    private long totalElements;
    private int currentPage;

    public MyPostResponseDTO(Page<Post> postPage) {
        this.posts = postPage.getContent().stream()
                .map(PostDTO::new)
                .collect(Collectors.toList());
        this.totalPages = postPage.getTotalPages();
        this.totalElements = postPage.getTotalElements();
        this.currentPage = postPage.getNumber();
    }

    @Getter
    public static class PostDTO {
        private Long id;
        private String title;
        private String thumbnail;
        private String genre;
        private LocalDateTime createdAt;

        public PostDTO(Post post) {
            this.id = post.getId();
            this.title = post.getTitle();
            this.thumbnail = post.getThumbnail();
            this.genre = post.getGenre().toString();
            this.createdAt = post.getCreatedAt();
        }
    }
}