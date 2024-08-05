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

    public ResponseEntity<Page<Post>> getResultOfPostSearch(@RequestParam("id") Long userId, @RequestParam("title") String title, @RequestParam("page") int page) {
        searchLogService.saveRecentSearchLog(userId, title);
        return ResponseEntity.ok(searchService.getPostsByTitle(title, page));
    }

    @RequestMapping(value = "/innovel/search/users", method = RequestMethod.GET)
    public ResponseEntity<Page<User>> getResultOfUserSearch(@RequestParam("id") Long userId, @RequestParam("username") String username, @RequestParam("page") int page) {
        searchLogService.saveRecentSearchLog(userId, username);
        return ResponseEntity.ok(searchService.getUsersByUsername(username, page));
    }

    @RequestMapping(value = "/innovel/search", method = RequestMethod.GET)
    public ResponseEntity<List<SearchLog>> getSearchLogs(@RequestParam("id") Long userId) {
        return ResponseEntity.ok(searchLogService.findRecentSearchLogs(userId));
    }
}
