package com.solux.innovel.post.postDBCheck;

import com.solux.innovel.models.Post;
import com.solux.innovel.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostDBCheckService {

    @Autowired
    private PostRepository postRepository;

    public Post getPostById(Long id) {
        Optional<Post> post = postRepository.findById(id);
        return post.orElse(null); // 반환할 수 있는 null 체크 또는 예외 처리 추가
    }
}
