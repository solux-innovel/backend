package com.solux.innovel.post.postSave;

import com.solux.innovel.models.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/innovel/posts")
@RequiredArgsConstructor
public class PostSaveController {
    private final PostSaveService postsaveService;

    @PostMapping("/save")
    public ResponseEntity<Post> savePost(@RequestBody PostSaveDTO postSaveRequestDTO) {
        Post savedPost = postsaveService.savePost(postSaveRequestDTO);
        return ResponseEntity.ok(savedPost);
    }
}
