package com.example.redeamerica.controller;

import com.example.redeamerica.dto.PostDTO;
import com.example.redeamerica.model.PostEntity;
import com.example.redeamerica.service.PostService;
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
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Post")
@RequestMapping("/blog")
public class PostController {

    @Autowired
    PostService postService;


    @Operation(summary = "Create a new post",
            description = "Creates a new post and saves it to the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post created successfully",
                    content = @Content(schema = @Schema(implementation = PostEntity.class))),
            @ApiResponse(responseCode = "403", description = "Error, invalid token or you do not have permissions to perform this action"),
            @ApiResponse(responseCode = "400", description = "Error creating post, incorrect data"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping(value = "create")
    public ResponseEntity<?> createPost(@RequestBody PostDTO postDTO) {
        try {
            PostEntity postCreated = postService.createPost(postDTO);
            return ResponseEntity.status(HttpStatus.OK).body(postCreated);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }


    @Operation(summary = "Get post by user",
            description = "Get publications created by the user, to be displayed in my works")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully obtains publications",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = PostEntity.class)))),
            @ApiResponse(responseCode = "403", description = "Error, invalid token or you do not have permissions to perform this action"),
            @ApiResponse(responseCode = "400", description = "Error getting posts"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @GetMapping
    public ResponseEntity<ArrayList<PostEntity>> getPostsByUser(){
        ArrayList<PostEntity> postsList = postService.getPostsByUser();
        return ResponseEntity.status(HttpStatus.OK).body(postsList);
    }


    @Operation(summary = "Delete a post by id",
            description = "Delete a post created in the database via an ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post successfully deleted ",
                    content = @Content(schema = @Schema(implementation = PostEntity.class))),
            @ApiResponse(responseCode = "403", description = "Error, invalid token or you do not have permissions to perform this action"),
            @ApiResponse(responseCode = "400", description = "Error removing post, incorrect id"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String>  deletePostById(@PathVariable Long id){
        boolean postDeleted = postService.deletePostById(id);
        if (postDeleted){
            return ResponseEntity.status(HttpStatus.OK).body("Publicación eliminada con éxito");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                "Hubo un error al eliminar la publicación, verifica los datos");
    }

}
