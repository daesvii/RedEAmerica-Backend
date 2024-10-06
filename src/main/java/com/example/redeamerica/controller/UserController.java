package com.example.redeamerica.controller;

import com.example.redeamerica.dto.*;
import com.example.redeamerica.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "User")
@RequestMapping("/profile")
public class UserController {

    @Autowired
    UserService userService;

    @Operation(summary = "Get user data",
            description = "Get the user's registered data in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User data successfully obtained",
                    content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "400", description = "Error obtaining user data, please review the information provided"),
            @ApiResponse(responseCode = "403", description = "Error, invalid token or you do not have permissions to perform this action"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("user")
    public ResponseEntity<?> getProfileInfoUser(){
        UserDTO user = userService.getProfileInfoUser();
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }


    @Operation(summary = "Obtain user data from redeamerica",
            description = "Obtain the registered data of the redeamerica user in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Redeamerica user data successfully obtained",
                    content = @Content(schema = @Schema(implementation = RedeamericaDTO.class))),
            @ApiResponse(responseCode = "400", description = "Error obtaining redeamerica user data, please review the information provided"),
            @ApiResponse(responseCode = "403", description = "Error, invalid token or you do not have permissions to perform this action"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @GetMapping("redeamerica")
    public ResponseEntity<?> getProfileInfoRedeamerica(){
        RedeamericaDTO user = userService.getProfileInfoRedeamerica();
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }


    @Operation(summary = "Update user information",
            description = "Update the user's information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User information successfully updated",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Error, check credentials"),
            @ApiResponse(responseCode = "403", description = "Error, invalid token or you do not have permissions to perform this action"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @PutMapping("update/user")
    public ResponseEntity<?> updateUserInfo(@RequestBody UserUpdateDTO userUpdateDTO){
        String response = userService.updateUserInfo(userUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @Operation(summary = "Update Redeamerica user information",
            description = "Update the Redeamerica user's information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Redeamerica user information successfully updated",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "403", description = "Error, invalid token or you do not have permissions to perform this action"),
            @ApiResponse(responseCode = "400", description = "Error, check credentials"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PutMapping("update/redeamerica")
    public ResponseEntity<?> updateUserRedeamerica(@RequestBody RedeamericaUpdateDTO redeamericaUpdateDTO){
        String response = userService.updateUserRedeamerica(redeamericaUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
