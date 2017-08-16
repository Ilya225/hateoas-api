package com.example.hateoasapi.domain;

import javax.persistence.Id;

import org.springframework.hateoas.ResourceSupport;

public class Category extends ResourceSupport {

    @Id
    private String id;
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public String getName(String name) {
        return name;
    }
}