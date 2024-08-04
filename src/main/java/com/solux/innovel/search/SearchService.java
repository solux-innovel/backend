package com.solux.innovel.search;

import com.solux.innovel.models.Post;
import com.solux.innovel.models.User;
import com.solux.innovel.post.PostRepository;
import com.solux.innovel.repository.UserRepository;
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
    private final UserRepository userRepository;

    public Page<Post> getPostsByTitle(String title, int page) {
        Pageable pageable = PageRequest.of(page, 20, Sort.by(Sort.Order.desc("createdAt")));
        System.out.println("Searching posts with title: " + title);
        Page<Post> result = postRepository.findPostsByTitle(title, pageable);
        System.out.println("Number of posts found: " + result.getTotalElements());
        return result;
    }

    public Page<User> getUsersByUsername(String username, int page) {
        Pageable pageable = PageRequest.of(page, 20, Sort.by(Sort.Order.asc("username")));
        System.out.println("Searching users with username: " + username);
        Page<User> result = userRepository.findByUsernameContaining(username, pageable);
        System.out.println("Number of users found: " + result.getTotalElements());
        return result;
    }
}
