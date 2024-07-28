package com.solux.innovel.userUpdate;

import com.solux.innovel.models.User;
import com.solux.innovel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> updateUserProfile(Long userId, ProfileUpdateRequest profileUpdateRequest) {
        return userRepository.findById(userId).map(user -> {
            user.updateUsername(profileUpdateRequest.getUsername());
            user.updateProfile(profileUpdateRequest.getProfileImageUrl());
            return userRepository.save(user);
        });
    }
}
