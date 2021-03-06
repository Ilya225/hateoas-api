package com.example.hateoasapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hateoasapi.domain.Category;
import com.example.hateoasapi.repository.CategoryRepository;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private CategoryRepository categoryRepository;

    public CategoryController( CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping(path = "/categories", method = { RequestMethod.GET })
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categoriesList = categoryRepository.findAll();
        return new ResponseEntity<>(categoriesList, HttpStatus.OK);
    }

    @RequestMapping(path = "/category/create", method = { RequestMethod.POST })
    public Category createCategory(@RequestBody Category input) {
        Category category = new Category(input.getName());
        categoryRepository.save(category);
        return category;
    }

    /**
     * Create Category via GET model
     */
    @RequestMapping(path = "/create_category", method = { RequestMethod.GET })
    public Category createCategoryGet(@RequestParam(name = "name", required = true) String name) {

        Category category = new Category(name);
        categoryRepository.save(category);
        return category;
    }
}