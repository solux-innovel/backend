package com.solux.innovel.mypage.mypost;

import com.solux.innovel.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MyPostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE p.writer.id = :userId")
    Page<Post> findByWriterId(@Param("userId") Long userId, Pageable pageable);
}