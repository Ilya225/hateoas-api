package com.example.hateoasapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.hateoasapi.domain.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
    
}