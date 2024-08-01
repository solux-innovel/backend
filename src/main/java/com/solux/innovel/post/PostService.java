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

    @Transactional(readOnly = true)
    public Page<Post> getPostsByTitle(String title, int page) {
        Pageable pageable = PageRequest.of(page, 20, Sort.by(Sort.Order.desc("createdAt")));
        return postRepository.findPostsByTitle(title, pageable);
    }
}
