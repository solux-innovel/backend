package com.solux.innovel.post;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solux.innovel.models.Post;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecentPostService {

    private final ObjectMapper objectMapper;
    private final PostRepository postRepository;

    public List<Post> saveRecentPostToCookie(Long postId, HttpServletResponse response, HttpServletRequest request) throws IOException {
        List<Post> recentPosts = getRecentPostsFromCookie(request);
        log.debug("Retrieved recent posts from cookie: {}", recentPosts);

        // 게시물이 이미 목록에 있는지 확인
        boolean alreadyExists = recentPosts.stream().anyMatch(post -> post.getId().equals(postId));

        if (alreadyExists) {
            log.debug("Post with ID {} is already in recent posts list, not adding it again", postId);
        } else {
            // 최근 읽은 게시물이 10개를 초과하면 가장 오래된 게시물 삭제
            if (recentPosts.size() >= 10) {
                recentPosts.remove(recentPosts.size() - 1);
                log.debug("Removed oldest post from recent posts list");
            }

            // 게시물 추가
            Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new NoSuchElementException("Post not found with id: " + postId));
            recentPosts.add(post);
            log.debug("Added post with ID {} to recent posts list", postId);
        }

        // JSON으로 변환 및 인코딩
        String recentPostsJson = objectMapper.writeValueAsString(recentPosts);
        log.debug("Serialized recent posts to JSON: {}", recentPostsJson);

        String encodedRecentPostsJson = URLEncoder.encode(recentPostsJson, StandardCharsets.UTF_8);

        // 쿠키 설정
        Cookie cookie = new Cookie("recentPosts", encodedRecentPostsJson);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 7); // 1주일 동안 유지
        response.addCookie(cookie);
        log.debug("Saved recent posts to cookie");

        return recentPosts;
    }

    public List<Post> getRecentPostsFromCookie(HttpServletRequest request) throws IOException {
        Cookie[] cookies = request.getCookies();
        String cookieValue = getCookieValue(cookies, "recentPosts");

        if (cookieValue != null) {
            String decodedCookieValue = URLDecoder.decode(cookieValue, StandardCharsets.UTF_8);
            return objectMapper.readValue(decodedCookieValue, new TypeReference<List<Post>>() {});
        }

        return new ArrayList<>();
    }

    private String getCookieValue(Cookie[] cookies, String name) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
