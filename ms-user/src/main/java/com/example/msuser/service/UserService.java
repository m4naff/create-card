package com.example.msuser.service;

import com.example.msuser.dao.entity.UserEntity;
import com.example.msuser.dao.repository.UserRepository;
import com.example.msuser.dto.request.SignInRequest;
import com.example.msuser.dto.request.SignUpRequest;
import com.example.msuser.dto.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public UserEntity findUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Could not find user with email:" + email));
    }

    public void signUp(SignUpRequest request){
        var checkUser = userRepository.findByEmail(request.getEmail());
        if(checkUser.isPresent()){
            return;
        }

        UserEntity user = UserEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
    }

    public AuthenticationResponse signIn(SignInRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );

        var user = findUserByEmail(request.getEmail());
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
