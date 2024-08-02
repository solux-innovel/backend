package com.solux.innovel.post.postDBCheck;

import com.solux.innovel.models.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@Slf4j
public class PostDBCheckController {
    @Autowired
    private PostDBCheckService postdbService;

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable("id") Long id) {
        Post post = postdbService.getPostById(id);
        if (post != null) {
            log.info("Post with ID {} found and returned successfully.", id);
            return ResponseEntity.ok(post);
        } else {
            log.info("Post with ID {} not found.", id);
            return ResponseEntity.notFound().build();
        }
    }
}