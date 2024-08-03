package com.solux.innovel.post;

import com.solux.innovel.models.Post;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final PostService postService;
    private final RecentPostService recentPostService;

    @RequestMapping(value = "/innovel/posts/genre", method = RequestMethod.POST)
    public ResponseEntity<Page<Post>> showPostsByGenre(@RequestParam("page") int page, @RequestParam("genre") String genre) {
        return ResponseEntity.ok(postService.getPostsByGenre(page, genre));
    }

    @RequestMapping(value = "/innovel/posts/recent-read/list")
    public ResponseEntity<List<Post>> getRecentPosts(HttpServletRequest request) {
        try {
            return new ResponseEntity<>(recentPostService.getRecentPostsFromCookie(request), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/innovel/posts/recent-read/add")
    public ResponseEntity<HttpStatus> addRecentPost(@RequestParam("postId") Long postId, HttpServletRequest request, HttpServletResponse response) {
        try {
            recentPostService.saveRecentPostToCookie(postId, response, request);
        } catch (IOException e1) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (RuntimeException e2) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // 마이페이지 - 내가 창작한 소설 내에서, 소설 썸네일 클릭 시 나오는 화면
    // 내의 "삭제"와 "수정" 기능 수행 시 -> db 업데이트
    @RequestMapping(value = "/innovel/mypage/mypost/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePost(@PathVariable("id")  Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value = "/innovel/mypage/mypost/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Post> updatePost(@PathVariable("id") Long id, @RequestBody PostUpdateDTO postupdateDTO) {
        Post post = new Post();
        post.setTitle(postupdateDTO.getTitle());
        post.setContent(postupdateDTO.getContent());
        Post updatedPost = postService.updatePost(id, post);
        return ResponseEntity.ok(updatedPost);
    }

    @GetMapping("/innovel/posts/{id}")
    public ResponseEntity<Post> getPost(@PathVariable("id") Long id) {
        Post post = postService.getPostById(id);
        if (post != null) {
            log.info("Post with ID {} found and returned successfully.", id);
            return ResponseEntity.ok(post);
        } else {
            log.info("Post with ID {} not found.", id);
            return ResponseEntity.notFound().build();
        }
    }
}
