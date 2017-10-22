package com.example.hateoasapi.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.hateoasapi.domain.Post;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    Post findByTitle(String title);

    List<Post> findByCategoryId(String id);
}