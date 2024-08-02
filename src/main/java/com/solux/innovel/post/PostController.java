package com.solux.innovel.post;

import com.solux.innovel.models.Post;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final RecentPostService recentPostService;

    @RequestMapping(value = "/innovel/posts/new", method = RequestMethod.POST)
    public ResponseEntity<Page<Post>> showPostsByGenre(@RequestParam("page") int page, @RequestParam("genre") String genre) {
        return ResponseEntity.ok(postService.getPostsByGenre(page, genre));
    }

    @RequestMapping(value = "/innovel/posts/recent-read/list", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> getRecentPosts(HttpServletRequest request) {
        try {
            return new ResponseEntity<>(recentPostService.getRecentPostsFromCookie(request), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/innovel/posts/recent-read/add", method = RequestMethod.POST)
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
}
