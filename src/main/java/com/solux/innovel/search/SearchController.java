package com.solux.innovel.search;

import com.solux.innovel.models.Post;
import com.solux.innovel.models.User;
import com.solux.innovel.search.searchlog.SearchLog;
import com.solux.innovel.search.searchlog.SearchLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;
    private final SearchLogService searchLogService;

    @RequestMapping(value = "/innovel/search/posts", method = RequestMethod.GET)
    public ResponseEntity<?> getResultOfPostSearch(
            @RequestParam("id") String socialId,
            @RequestParam("title") String title,
            @RequestParam(value = "page", defaultValue = "0") int page) {
        searchLogService.saveRecentSearchLogBySocialId(socialId, title);
        try {
            Page<Post> result = searchService.getPostsByTitle(title, page);
            if(result.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("An error occurred: " + e.getMessage());
        }
    }


    @RequestMapping(value = "/innovel/search/users", method = RequestMethod.GET)
    public ResponseEntity<?> getResultOfUserSearch(
            @RequestParam("id") String socialId,
            @RequestParam("username") String username,
            @RequestParam(value = "page", defaultValue = "0") int page) {
        searchLogService.saveRecentSearchLogBySocialId(socialId, username);
        try {
            Page<User> result = searchService.getUsersByUsername(username, page);
            if (result.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("An error occurred: " + e.getMessage());
        }
    }


    @RequestMapping(value = "/innovel/search", method = RequestMethod.GET)
    public ResponseEntity<List<SearchLog>> getSearchLogs(@RequestParam("id") String socialId) {
        return ResponseEntity.ok(searchLogService.findRecentSearchLogsBySocialId(socialId));
    }
}

