package com.solux.innovel.mypage.mypost;

import com.solux.innovel.mypage.mypost.dto.MyPostResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/innovel/mypage/mypost")
public class MyPostController {
    private final MyPostService myPostService;
//    @GetMapping
//    public MyPostResponseDTO getMyPosts(@RequestParam(defaultValue = "0") int page,
//                                        @RequestParam(defaultValue = "10") int size,
//                                        @RequestHeader("Authorization") String token) {
//        String accessToken = token.substring(7);
//        Long userId = jwtTokenProvider.extractUserId(accessToken);
//        return myPostService.getMyPosts(userId, page, size);
//    }
//}

    @GetMapping
    public MyPostResponseDTO getMyPosts(@RequestParam(defaultValue = "0") int page, // 첫 번째 페이지 인덱스를 0
                                        @RequestParam(defaultValue = "12") int size //
                                        //@RequestHeader("Authorization") String token
    ) {
        //String accessToken = token.substring(7);
        //Long userId = jwtTokenProvider.extractUserId(accessToken);
        Long userId = 1L; // 테스트용 고정 사용자 ID
        return myPostService.getMyPosts(userId, page, size);
    }
}