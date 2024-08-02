package com.solux.innovel.search;

import com.solux.innovel.models.Post;
import com.solux.innovel.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @RequestMapping(value = "/innovel/posts/search", method = RequestMethod.GET)
    public ResponseEntity<Page<Post>> getResultOfPostSearch(@RequestParam("title") String title, @RequestParam("page") int page) {
        return ResponseEntity.ok(searchService.getPostsByTitle(title, page));
    }
}
