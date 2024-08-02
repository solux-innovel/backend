package com.solux.innovel.post;

import com.solux.innovel.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post p where p.genre = :genre")
    Page<Post> findPostsByGenre(String genre, Pageable pageable);

    Page<Post> findPostsByTitle(String title, Pageable pageable);
}
