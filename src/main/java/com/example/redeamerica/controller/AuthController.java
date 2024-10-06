package com.example.redeamerica.controller;

import com.example.redeamerica.security.auth.AuthResponse;
import com.example.redeamerica.security.auth.LoginRequest;
import com.example.redeamerica.security.auth.RegisterRequest;
import com.example.redeamerica.security.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @Operation(summary = "Login",
            description = "Login to the application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "session logged in successfully"),
            @ApiResponse(responseCode = "403", description = "Error, invalid token or you do not have permissions to perform this action"),
            @ApiResponse(responseCode = "400", description = "Failed to log in, please check credentials"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }


    @Operation(summary = "Register",
            description = "Register in the app")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registration completed successfully"),
            @ApiResponse(responseCode = "403", description = "Error, invalid token or you do not have permissions to perform this action"),
            @ApiResponse(responseCode = "400", description = "Error registering"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }

}
