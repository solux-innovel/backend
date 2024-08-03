package com.solux.innovel.mypage.mypost;

import com.solux.innovel.mypage.mypost.dto.MyPostResponseDTO;
import com.solux.innovel.repository.UserRepository;
import com.solux.innovel.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/innovel/mypage/mypost")
public class MyPostController {
    private final MyPostService myPostService;
    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<?> getMyPosts(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "12") int size,
            @RequestParam(name = "socialId", required = true) String socialId) {
        try {
            Long userId;

            if (socialId != null) {
                // 실제 운영 환경에서 사용
                User user = userRepository.findBySocialId(socialId)
                        .orElseThrow(() -> new RuntimeException("User not found"));
                userId = user.getId();
            } else {
                // 테스트를 위해 userId를 1로 고정
                userId = 1L;
            }

            MyPostResponseDTO response = myPostService.getMyPosts(userId, page, size);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error occurred: " + e.getMessage());
        }
    }
}