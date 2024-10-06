package com.example.redeamerica.security.service;

import com.example.redeamerica.security.auth.AuthResponse;
import com.example.redeamerica.security.auth.LoginRequest;
import com.example.redeamerica.security.auth.RegisterRequest;
import com.example.redeamerica.model.ERole;
import com.example.redeamerica.model.UserEntity;
import com.example.redeamerica.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),
                request.getPassword()));

        UserDetails user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
        UserEntity user = new UserEntity();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        user.setLastName(request.getLastName());
        user.setPhone(request.getPhone());
        user.setCountry(request.getCountry());
        if (request.getEmail().equals("redeamerica@admin.com")){
            user.setRoles(ERole.ADMIN);
        }else {
            user.setRoles(ERole.USER);
        }
        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
