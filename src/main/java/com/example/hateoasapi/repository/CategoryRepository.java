package com.example.hateoasapi.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.hateoasapi.domain.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {
    public Category findByTitle(String title);
}