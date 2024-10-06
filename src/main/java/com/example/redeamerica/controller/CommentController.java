package com.example.redeamerica.controller;

import com.example.redeamerica.dto.CommentDTO;
import com.example.redeamerica.model.CommentEntity;
import com.example.redeamerica.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
@RequestMapping("/forum")
@Tag(name = "Comment")
public class CommentController {

    @Autowired
    CommentService commentService;


    @Operation(summary = "Make a comment",
            description = "Approve membership request to a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment created successfully",
                    content = @Content(schema = @Schema(implementation = CommentEntity.class))),
            @ApiResponse(responseCode = "403", description = "Error, invalid token or you do not have permissions to perform this action"),
            @ApiResponse(responseCode = "400", description = "Error creating comment"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping()
    public ResponseEntity<?> createComment(@RequestBody CommentDTO commentDTO) {
        try {
            CommentEntity commentCreated = commentService.createComment(commentDTO);
            return ResponseEntity.status(HttpStatus.OK).body(commentCreated);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }


    @Operation(summary = "Get comments by post id",
            description = "Get comments made on a post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully obtains comments",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CommentEntity.class)))),
            @ApiResponse(responseCode = "403", description = "Error, invalid token or you do not have permissions to perform this action"),
            @ApiResponse(responseCode = "400", description = "Error getting comments"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("{id}")
    public ResponseEntity<ArrayList<CommentEntity>> getAllComments(@PathVariable Long id){
        ArrayList<CommentEntity> listComments = commentService.getAllComments(id);
        return ResponseEntity.status(HttpStatus.OK).body(listComments);
    }
}
