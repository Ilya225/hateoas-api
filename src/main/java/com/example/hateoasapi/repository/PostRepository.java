package com.example.hateoasapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.hateoasapi.domain.Post;

public interface PostRepository extends MongoRepository<Post, String> {
    public Post findByTitle(String title);
}