package com.solux.innovel.userUpdate;

import com.solux.innovel.OAuth.dto.JwtTokenProvider;
import com.solux.innovel.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserProfileController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PutMapping("/profile")
    public ResponseEntity<User> updateUserProfile(@RequestHeader("Authorization") String token,
                                                  @RequestBody ProfileUpdateRequest profileUpdateRequest) {
        String tokenValue = token.replace("Bearer ", "");
        Long userId = jwtTokenProvider.extractUserId(tokenValue);
        return userService.updateUserProfile(userId, profileUpdateRequest)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}