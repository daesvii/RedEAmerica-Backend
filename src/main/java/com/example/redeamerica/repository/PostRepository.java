package com.example.redeamerica.repository;

import com.example.redeamerica.model.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    ArrayList<PostEntity> findByUserId(Long id);
}
