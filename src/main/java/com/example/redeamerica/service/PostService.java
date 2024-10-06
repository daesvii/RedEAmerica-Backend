package com.example.redeamerica.service;

import com.example.redeamerica.dto.PostDTO;
import com.example.redeamerica.model.PostEntity;
import com.example.redeamerica.model.UserEntity;
import com.example.redeamerica.repository.PostRepository;
import com.example.redeamerica.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    public PostEntity createPost (PostDTO postDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserEntity userFound = getByEmailAuthenticated();

        PostEntity newPost = new PostEntity();

        newPost.setUserId(userFound.getId());
        newPost.setContent(postDTO.getContent());
        newPost.setMediaUrl(postDTO.getMediaUrl());
        newPost.setCategory(postDTO.getCategory());
        newPost.setTimestamp(LocalDateTime.now());
        newPost.setCountry(postDTO.getCountry());
        return postRepository.save(newPost);
    }



    public ArrayList<PostEntity> getAllPosts() {
        // Returns a list of all posts in the database.
        return (ArrayList<PostEntity>) postRepository.findAll();
    }


    public ArrayList<PostEntity> getPostsByUser (){
        UserEntity userFound = getByEmailAuthenticated();
        return (ArrayList<PostEntity>) postRepository.findByUserId(userFound.getId());
    }

    public UserEntity getByEmailAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();

            Optional<UserEntity> userFound = userRepository.findByEmail(username);
            return userFound.orElseThrow();
        }
        throw new IllegalArgumentException("No hay un usuario logeado");
    }


    public boolean deletePostById(Long id){
        try {
            Optional<PostEntity> postFound = postRepository.findById(id);

            Long userId = postFound.get().getUserId();
            UserEntity userFound = getByEmailAuthenticated();

            if (userFound.getId() == userId) {
                postRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

}
