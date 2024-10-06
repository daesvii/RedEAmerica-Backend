package com.example.redeamerica.controller;

import com.example.redeamerica.model.PostEntity;
import com.example.redeamerica.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("home")
@RequiredArgsConstructor
@Tag(name = "Home")
public class HomeController {

    @Autowired
    PostService postService;


    @Operation(summary = "Get all posts",
            description = "Get all posts from database to display home")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully obtains posts",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = PostEntity.class)))),
            @ApiResponse(responseCode = "400", description = "Error getting posts"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping()
    public ResponseEntity<ArrayList<PostEntity>> getAllPosts(){
        ArrayList<PostEntity> listPosts = postService.getAllPosts();
        return ResponseEntity.status(HttpStatus.OK).body(listPosts);
    }
}
