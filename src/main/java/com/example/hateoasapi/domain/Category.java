package com.example.hateoasapi.domain;

import org.springframework.hateoas.ResourceSupport;

public class Category extends ResourceSupport {

    private String name;

    public Category(String name) {
        this.name = name;
    }

    public String getName(String name) {
        return name;
    }
}