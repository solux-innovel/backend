package com.solux.innovel.mypage.mypost;

import com.solux.innovel.OAuth.dto.JwtTokenProvider;
import com.solux.innovel.mypage.mypost.dto.MyPostResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/innovel/mypage/mypost")
public class MyPostController {
    private final MyPostService myPostService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping
    public MyPostResponseDTO getMyPosts(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size,
                                        @RequestHeader("Authorization") String token) {
        String accessToken = token.substring(7);
        Long userId = jwtTokenProvider.extractUserId(accessToken);
        return myPostService.getMyPosts(userId, page, size);
    }
}