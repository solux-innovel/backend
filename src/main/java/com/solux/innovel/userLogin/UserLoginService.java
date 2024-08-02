package com.solux.innovel.userLogin;

import com.solux.innovel.models.User;
import com.solux.innovel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserLoginService {
    private final UserRepository userRepository;

    public User saveNaverUser(UserLoginDTO userLoginDTO) {
        Optional<User> existingUser = userRepository.findBySocialId(userLoginDTO.getId());

        if (existingUser.isPresent()) {
            // 이미 존재하는 사용자
            return existingUser.get();
        } else {
            // 새로운 사용자 생성 및 저장
            User newUser = User.builder()
                    .email(userLoginDTO.getEmail())
                    .username(userLoginDTO.getNickname())
                    .socialId(userLoginDTO.getId())
                    .build();
            return userRepository.save(newUser);
        }
    }
}