package com.example.hateoasapi.domain;


import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Post extends ResourceSupport {

    private String body;
    private String title;
    private String category;
    private String created;

    @JsonCreator
    public Post(String body) {
        this.body = body;
    }

    public String getBody() {
        return this.body;
    }

    public String getTitle() {
        return this.title;
    }

    public String getCategory() {
        return this.category;
    }

    public String getCreated() {
        return this.created;
    }
}