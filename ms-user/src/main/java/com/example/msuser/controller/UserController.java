package com.example.msuser.controller;

import com.example.msuser.dao.entity.UserEntity;
import com.example.msuser.dto.request.SignInRequest;
import com.example.msuser.dto.request.SignUpRequest;
import com.example.msuser.dto.response.AuthenticationResponse;
import com.example.msuser.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    @GetMapping("search")
    public ResponseEntity<UserEntity> findUserByEmail(@RequestParam String email){
        return ResponseEntity.ok(userService.findUserByEmail(email));
    }

    @PostMapping("sign-up")
    public void signUp(@RequestBody SignUpRequest signUpRequest){
        userService.signUp(signUpRequest);
    }

    @GetMapping("sign-in")
    public AuthenticationResponse signIn(@RequestBody SignInRequest request){
        return userService.signIn(request);
    }
}
