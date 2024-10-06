package com.example.redeamerica.controller;

import com.example.redeamerica.model.MembershipRequestEntity;
import com.example.redeamerica.service.MembershipManagementService;
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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Membership management")
public class MembershipManagementController {

    @Autowired
    MembershipManagementService membershipManagementService;


    @Operation(summary = "Get membership requests",
            description = "Get membership requests created by users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully obtains membership requests",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = MembershipRequestEntity.class)))),
            @ApiResponse(responseCode = "403", description = "Error, invalid token or you do not have permissions to perform this action"),
            @ApiResponse(responseCode = "400", description = "Error getting membership requests"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping
    public ResponseEntity<ArrayList<MembershipRequestEntity>> getAllMembershipRequests(){
        ArrayList<MembershipRequestEntity> requestList = membershipManagementService.getAllMembershipRequests();
        return ResponseEntity.status(HttpStatus.OK).body(requestList);
    }


    @Operation(summary = "Approve membership request",
            description = "Approve membership request to a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Membership application successfully approved",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "403", description = "Error, invalid token or you do not have permissions to perform this action"),
            @ApiResponse(responseCode = "400", description = "Error approving request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PutMapping("approve/{requestId}")
    public ResponseEntity<?> approveMembershipRequest(@PathVariable Long requestId) {
        try {
            String response = membershipManagementService.approveMembershipRequest(requestId);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }


    @Operation(summary = "Decline membership request",
            description = "Reject a user's membership request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Membership application successfully rejected",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "403", description = "Error, invalid token or you do not have permissions to perform this action"),
            @ApiResponse(responseCode = "400", description = "Error rejecting request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PutMapping("decline/{requestId}")
    public ResponseEntity<?> declineMembershipRequest(@PathVariable Long requestId) {
        try {
            String response = membershipManagementService.declineMembershipRequest(requestId);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
