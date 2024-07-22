package com.solux.innovel.post;

import com.solux.innovel.models.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @RequestMapping(value = "/innovel/posts/new")
    public ResponseEntity<Page<Post>> showPostsByGenre(@RequestParam("page") int page, @RequestParam("genre") String genre) {
        return ResponseEntity.ok(postService.getPostsByGenre(page, genre));
    }

}
