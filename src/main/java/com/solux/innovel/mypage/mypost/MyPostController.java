package com.solux.innovel.mypage.mypost;

import com.solux.innovel.mypage.mypost.dto.MyPostResponseDTO;
import com.solux.innovel.repository.UserRepository;
import com.solux.innovel.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/innovel/mypage/mypost")
//public class MyPostController {
//    private final MyPostService myPostService;
//    private final UserRepository userRepository;
//
//    @GetMapping
//    public ResponseEntity<MyPostResponseDTO> getMyPosts(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "12") int size,
//            @RequestHeader("X-Social-Id") String socialId) {
//
//        User user = userRepository.findBySocialId(socialId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        MyPostResponseDTO response = myPostService.getMyPosts(user.getId(), page, size);
//        return ResponseEntity.ok(response);
//    }
//}

@RestController
@RequiredArgsConstructor
@RequestMapping("/innovel/mypage/mypost")
public class MyPostController {
    private final MyPostService myPostService;
    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<MyPostResponseDTO> getMyPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size) {
        // 테스트를 위해 userId를 1로 고정
        Long userId = 1L;
        MyPostResponseDTO response = myPostService.getMyPosts(userId, page, size);
        return ResponseEntity.ok(response);
    }
}