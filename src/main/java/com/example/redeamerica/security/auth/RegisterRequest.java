package com.example.redeamerica.security.auth;

import com.example.redeamerica.model.ERole;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @Email
    private String email;

    private String password;
    private String name;
    private String lastName;
    private String phone;
    private String country;
    private ERole role;
}
