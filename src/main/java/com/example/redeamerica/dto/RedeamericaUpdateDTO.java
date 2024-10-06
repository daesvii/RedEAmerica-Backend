package com.example.redeamerica.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RedeamericaUpdateDTO {
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String country;

    private String typeMembreship;
    private String address;
    private String identificationType;
    private String identificationNumber;
    private String urlIdentificationScan;
    private String educationLevel;
    private String urlEducationCertificate;
}
