package com.example.redeamerica.dto;

import com.example.redeamerica.model.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RedeamericaDTO {
    private long id;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private String country;
    private ERole roles;


    private String typeMembreship;
    private String address;
    private String identificationType;
    private String identificationNumber;
    private String urlIdentificationScan;
    private String educationLevel;
    private String urlEducationCertificate;
}
