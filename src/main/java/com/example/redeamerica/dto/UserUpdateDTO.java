package com.example.redeamerica.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateDTO {
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String country;
}
