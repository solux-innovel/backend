package com.solux.innovel.post.postSave;

import com.solux.innovel.models.Genre;
import com.solux.innovel.models.Post;
import com.solux.innovel.post.PostRepository;
import com.solux.innovel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostSaveService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Post savePost(PostSaveDTO postSaveRequestDTO) {
        Post post = new Post();
        post.setWriter(userRepository.findById(postSaveRequestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found")));
        post.setTitle(postSaveRequestDTO.getTitle());
        post.setContent(postSaveRequestDTO.getContent());
        post.setGenre(Genre.valueOf(postSaveRequestDTO.getGenre())); // Enum 변환
        return postRepository.save(post);
    }
}
