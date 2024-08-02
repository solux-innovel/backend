package com.solux.innovel.search;

import com.solux.innovel.models.Post;
import com.solux.innovel.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final PostRepository postRepository;

    public Page<Post> getPostsByTitle(String title, int page) {
        Pageable pageable = PageRequest.of(page, 20, Sort.by(Sort.Order.desc("createdAt")));
        return postRepository.findPostsByTitle(title, pageable);
    }
}
