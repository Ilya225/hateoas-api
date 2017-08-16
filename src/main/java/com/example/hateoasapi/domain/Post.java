package com.example.hateoasapi.domain;

import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.hateoas.ResourceSupport;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import com.example.hateoasapi.controller.*;

public class Post extends ResourceSupport {

    @Id
    private String _id;
    //@DBRef
    //private Category category;
    private String body;
    private String title;
    private String created;
    private String updated;

    //@JsonCreator
    public Post(String title, String body) {
        this.body = body;
        this.title = title;
    }

    public void setLinks() {
        this.add(linkTo(methodOn(PostController.class).getAllPosts()).withSelfRel());
    }

    public String getBody() {
        return this.body;
    }

    public String getTitle() {
        return this.title;
    }

    public String getCreated() {
        return this.created;
    }

    public String get_Id() {
        return _id;
    }



    /*public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return this.category;
    }*/
}