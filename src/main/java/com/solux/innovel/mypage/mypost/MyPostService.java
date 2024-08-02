package com.solux.innovel.mypage.mypost;

import com.solux.innovel.models.Post;
import com.solux.innovel.mypage.mypost.dto.MyPostResponseDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MyPostService {
    private final MyPostRepository myPostRepository;

    @Transactional(readOnly = true)
    public MyPostResponseDTO getMyPosts(Long user_id, int page, int size) {
        Page<Post> postPage = myPostRepository.findByWriterId(user_id,
                PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt")));

        return new MyPostResponseDTO(postPage);
    }
}