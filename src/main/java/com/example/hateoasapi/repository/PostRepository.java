package com.example.hateoasapi.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.hateoasapi.domain.Customer;
import com.example.hateoasapi.domain.Post;

public interface PostRepository extends MongoRepository<Post, String> {
    public Post findByTitle(String title);
}