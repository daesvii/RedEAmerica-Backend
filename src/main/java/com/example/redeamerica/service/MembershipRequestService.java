package com.example.redeamerica.service;

import com.example.redeamerica.dto.MembershipRequestDTO;
import com.example.redeamerica.model.ERequestStatus;
import com.example.redeamerica.model.MembershipRequestEntity;
import com.example.redeamerica.model.UserEntity;
import com.example.redeamerica.repository.MembershipRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MembershipRequestService {

    @Autowired
    private MembershipRequestRepository membershipRequestRepository;

    @Autowired
    PostService postService;

    public MembershipRequestEntity requestMembership(MembershipRequestDTO membershipRequestDTO) {

        UserEntity userFound = postService.getByEmailAuthenticated();

        Optional<MembershipRequestEntity> membershipFound = membershipRequestRepository.findByUserId(userFound.getId());

        if (membershipFound.isPresent()){
            throw new IllegalArgumentException("Error!, Ya hay una postulación en curso");
        }

        MembershipRequestEntity membershipRequest = new MembershipRequestEntity();
        membershipRequest.setUserId(userFound.getId());
        membershipRequest.setTypeMembreship(membershipRequestDTO.getTypeMembreship());
        membershipRequest.setAddress(membershipRequestDTO.getAddress());
        membershipRequest.setIdentificationType(membershipRequestDTO.getIdentificationType());
        membershipRequest.setIdentificationNumber(membershipRequestDTO.getIdentificationNumber());
        membershipRequest.setUrlIdentificationScan(membershipRequestDTO.getUrlIdentificationScan());
        membershipRequest.setEducationLevel(membershipRequestDTO.getEducationLevel());
        membershipRequest.setUrlEducationCertificate(membershipRequestDTO.getUrlEducationCertificate());
        membershipRequest.setRequestStatus(ERequestStatus.PENDIENTE);

        return membershipRequestRepository.save(membershipRequest);
    }
}
