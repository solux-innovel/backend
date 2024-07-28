package com.solux.innovel.post;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solux.innovel.models.Post;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecentPostService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final PostRepository postRepository;

    public List<Post> saveRecentPostToCookie(Long postId, HttpServletResponse response, HttpServletRequest request) throws IOException {
        List<Post> recentPosts = getRecentPostsFromCookie(request);
        if (recentPosts.size() > 10) {
            recentPosts.remove(recentPosts.size() - 1);
        }

        recentPosts.add(postRepository.findById(postId).orElseThrow(
                () -> new RuntimeException("Post Not Found")));
        String recentPostsJson = objectMapper.writeValueAsString(recentPosts);
        String encodedRecentPostsJson = URLEncoder.encode(recentPostsJson, StandardCharsets.UTF_8);
        Cookie cookie = new Cookie("recentPosts", encodedRecentPostsJson);
        cookie.setPath("/");
        response.addCookie(cookie);
        return recentPosts;
    }

    public List<Post> getRecentPostsFromCookie(HttpServletRequest request) throws IOException {
        Cookie[] cookies = request.getCookies();
        String cookieValue = getCookieValue(cookies, "recentPosts");

        if (cookieValue != null) {
            String decodedCookieValue = URLDecoder.decode(cookieValue, StandardCharsets.UTF_8);
            return objectMapper.readValue(decodedCookieValue, new TypeReference<>() {});
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
