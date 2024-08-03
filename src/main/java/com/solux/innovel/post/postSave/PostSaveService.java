package com.solux.innovel.post.postSave;

import com.solux.innovel.models.Genre;
import com.solux.innovel.models.Post;
import com.solux.innovel.models.User;
import com.solux.innovel.post.PostRepository;
import com.solux.innovel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostSaveService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Post savePost(PostSaveDTO postSaveDTO) {
        User writer = userRepository.findBySocialId(postSaveDTO.getSocialId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with social ID: " + postSaveDTO.getSocialId()));


        Post post = new Post();
        post.setWriter(writer);
        post.setTitle(postSaveDTO.getTitle());
        post.setGenre(Genre.valueOf(postSaveDTO.getGenre().toUpperCase()));
        post.setContent(postSaveDTO.getContent());

        return postRepository.save(post);
    }
}

