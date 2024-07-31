package com.solux.innovel.userLogin;

import com.solux.innovel.models.User;
import com.solux.innovel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginService {
    private final UserRepository userRepository;

    public User saveNaverUser(UserLoginDTO userloginDTO) {
        User user = User.builder()
                .email(userloginDTO.getEmail())
                .username(userloginDTO.getNickname())
                .socialId(userloginDTO.getId())
                .build();
        return userRepository.save(user);
    }
}