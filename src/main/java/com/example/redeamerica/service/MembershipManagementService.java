package com.example.redeamerica.service;

import com.example.redeamerica.model.*;
import com.example.redeamerica.repository.MembershipRequestRepository;
import com.example.redeamerica.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MembershipManagementService {

    @Autowired
    MembershipRequestRepository membershipRequestRepository;

    @Autowired
    UserRepository userRepository;

    public ArrayList<MembershipRequestEntity> getAllMembershipRequests() {
        return (ArrayList<MembershipRequestEntity>) membershipRequestRepository.findAll();
    }

    public String approveMembershipRequest (Long id){
        try {
            // The request is obtained to change the status from pending to approved.
            MembershipRequestEntity requestFound = membershipRequestRepository.findById(id).orElseThrow();
            requestFound.setRequestStatus(ERequestStatus.APROBADO);

            //The user is obtained, to change their user role to redeamerica
            Long userId = requestFound.getUserId();
            UserEntity userFound = userRepository.findById(userId).orElseThrow();
            userFound.setRoles(ERole.REDEAMERICA);

            // Changes are saved in the database
            membershipRequestRepository.save(requestFound);
            userRepository.save(userFound);

            return "Membresía aprobada con éxito";
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Error!, no se pudo aprobar la membresia, por favor verifica los datos");
        }



    }

    public String declineMembershipRequest (Long id){
        try {
            // The request is obtained to change the status from pending to decline.
            MembershipRequestEntity requestFound = membershipRequestRepository.findById(id).orElseThrow();
            requestFound.setRequestStatus(ERequestStatus.RECHAZADO);

            Long userId = requestFound.getUserId();
            UserEntity userFound = userRepository.findById(userId).orElseThrow();
            userFound.setRoles(ERole.USER);

            // Changes are saved in the database
            membershipRequestRepository.save(requestFound);
            userRepository.save(userFound);
            return "Membresía rechazada con éxito";
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Error!, no se pudo completar la operación, por favor verifica los datos");
        }

    }
}
