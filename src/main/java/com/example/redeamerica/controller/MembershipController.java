package com.example.redeamerica.controller;

import com.example.redeamerica.dto.MembershipRequestDTO;
import com.example.redeamerica.model.MembershipRequestEntity;
import com.example.redeamerica.service.MembershipRequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/membership")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
@Tag(name = "Membership")
public class MembershipController {

    @Autowired
    MembershipRequestService membershipRequestService;


    @Operation(summary = "Create a membership application",
            description = "Creates a membership request and saves it to the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Membership application created successfully",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = MembershipRequestEntity.class)))),
            @ApiResponse(responseCode = "403", description = "Error, invalid token or you do not have permissions to perform this action"),
            @ApiResponse(responseCode = "400", description = "Error creating membership request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping
    public ResponseEntity<?> requestMembership(@RequestBody MembershipRequestDTO membershipRequestDTO) {
        try {
            MembershipRequestEntity membershipRequestEntity = membershipRequestService.requestMembership(membershipRequestDTO);
            return ResponseEntity.status(HttpStatus.OK).body(membershipRequestEntity);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
