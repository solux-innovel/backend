package com.solux.innovel.mypage.mypost;

import com.solux.innovel.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyPostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByWriterId(Long writerId, Pageable pageable);
}