package com.example.redeamerica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "memberships")
public class MembershipRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String typeMembreship;

    private String address;

    @Column(name = "identification_type")
    private String identificationType;


    @Column(name = "identification_number")
    private String identificationNumber;


    @Column(name = "url_identification_scan")
    private String urlIdentificationScan;


    @Column(name = "education_level")
    private String educationLevel;


    @Column(name = "url_education_certificate")
    private String urlEducationCertificate;

    @Column(name = "request_status")
    @Enumerated(EnumType.STRING)
    private ERequestStatus requestStatus;
}
