package com.solux.innovel.post;

import com.solux.innovel.models.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public Page<Post> getPostsByGenre(int page, String genre) {
        Pageable pageable = PageRequest.of(page, 20, Sort.by(Sort.Order.desc("createdAt")));
        return postRepository.findPostsByGenre(genre, pageable);
    }

    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
        postRepository.delete(post);
    }

    @Transactional
    public Post updatePost(Long id, Post postDetails) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));

        post.updatePost(postDetails);

        return postRepository.save(post);
    }
}
