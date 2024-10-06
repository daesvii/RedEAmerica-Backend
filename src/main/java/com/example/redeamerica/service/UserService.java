package com.example.redeamerica.service;

import com.example.redeamerica.dto.RedeamericaDTO;
import com.example.redeamerica.dto.RedeamericaUpdateDTO;
import com.example.redeamerica.dto.UserDTO;
import com.example.redeamerica.dto.UserUpdateDTO;
import com.example.redeamerica.model.MembershipRequestEntity;
import com.example.redeamerica.model.UserEntity;
import com.example.redeamerica.repository.MembershipRequestRepository;
import com.example.redeamerica.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MembershipRequestRepository membershipRequestRepository;

    @Autowired
    PostService postService;

    public UserDTO getProfileInfoUser (){
        UserEntity userFound = postService.getByEmailAuthenticated();

        UserDTO userBuild = new UserDTO();
        userBuild.setId(userFound.getId());
        userBuild.setName(userFound.getName());
        userBuild.setLastName(userFound.getName());
        userBuild.setEmail(userFound.getEmail());
        userBuild.setPhone(userFound.getPhone());
        userBuild.setPassword(userFound.getPassword());
        userBuild.setCountry(userFound.getCountry());
        userBuild.setRoles(userFound.getRoles());
        return userBuild;
    }

    public RedeamericaDTO getProfileInfoRedeamerica (){
        UserEntity userFound = postService.getByEmailAuthenticated();

        RedeamericaDTO userBuild = new RedeamericaDTO();
        userBuild.setId(userFound.getId());
        userBuild.setName(userFound.getName());
        userBuild.setLastName(userFound.getName());
        userBuild.setEmail(userFound.getEmail());
        userBuild.setPhone(userFound.getPhone());
        userBuild.setPassword(userFound.getPassword());
        userBuild.setCountry(userFound.getCountry());
        userBuild.setRoles(userFound.getRoles());

        Long userId = userFound.getId();

        MembershipRequestEntity membershipRequest = membershipRequestRepository.findByUserId(userId).orElseThrow();

        userBuild.setTypeMembreship(membershipRequest.getTypeMembreship());
        userBuild.setAddress(membershipRequest.getAddress());
        userBuild.setIdentificationType(membershipRequest.getIdentificationType());
        userBuild.setIdentificationNumber(membershipRequest.getIdentificationNumber());
        userBuild.setUrlIdentificationScan(membershipRequest.getUrlIdentificationScan());
        userBuild.setEducationLevel(membershipRequest.getEducationLevel());
        userBuild.setUrlEducationCertificate(membershipRequest.getUrlEducationCertificate());
        return userBuild;
    }

    public String updateUserInfo (UserUpdateDTO userUpdateDTO){
        UserEntity userFound = postService.getByEmailAuthenticated();

        userFound.setName(userUpdateDTO.getName());
        userFound.setLastName(userUpdateDTO.getLastName());
        userFound.setEmail(userUpdateDTO.getEmail());
        userFound.setCountry(userUpdateDTO.getCountry());
        userFound.setPhone(userUpdateDTO.getPhone());

        userRepository.save(userFound);
        return "Información personal actualizada con éxito!";
    }

    public String updateUserRedeamerica (RedeamericaUpdateDTO redeamericaUpdateDTO){
        UserEntity userFound = postService.getByEmailAuthenticated();

        userFound.setName(redeamericaUpdateDTO.getName());
        userFound.setLastName(redeamericaUpdateDTO.getLastName());
        userFound.setEmail(redeamericaUpdateDTO.getEmail());
        userFound.setCountry(redeamericaUpdateDTO.getCountry());
        userFound.setPhone(redeamericaUpdateDTO.getPhone());

        userRepository.save(userFound);

        Long userId = userFound.getId();

        MembershipRequestEntity membershipRequest = membershipRequestRepository.findByUserId(userId).orElseThrow();

        membershipRequest.setAddress(redeamericaUpdateDTO.getAddress());
        membershipRequest.setIdentificationType(redeamericaUpdateDTO.getIdentificationType());
        membershipRequest.setIdentificationNumber(redeamericaUpdateDTO.getIdentificationNumber());
        membershipRequest.setUrlIdentificationScan(redeamericaUpdateDTO.getUrlIdentificationScan());
        membershipRequest.setEducationLevel(redeamericaUpdateDTO.getEducationLevel());
        membershipRequest.setUrlEducationCertificate(redeamericaUpdateDTO.getUrlEducationCertificate());
        membershipRequest.setTypeMembreship(redeamericaUpdateDTO.getTypeMembreship());

        membershipRequestRepository.save(membershipRequest);
        return "Información personal actualizada con éxito!";
    }
}
