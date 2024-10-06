package com.example.redeamerica.repository;

import com.example.redeamerica.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    ArrayList<CommentEntity> findByPostId(Long id);
}
