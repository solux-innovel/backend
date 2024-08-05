package com.solux.innovel.userLogin;

import com.solux.innovel.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/innovel/users")
@Slf4j
public class UserLoginController {
    private final UserLoginService userloginService;

    @PostMapping("/login")
    public ResponseEntity<User> saveUser(@RequestBody UserLoginDTO userloginDTO){
        try {
            User savedUser = userloginService.saveNaverUser(userloginDTO);
            return new ResponseEntity<>(savedUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
