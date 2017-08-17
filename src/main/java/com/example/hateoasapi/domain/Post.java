package com.example.hateoasapi.domain;

import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.hateoas.ResourceSupport;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.io.Serializable;

import com.example.hateoasapi.controller.*;

public class Post extends ResourceSupport implements Serializable {

    @Id
    private String _id;
    //@DBRef
    //private Category category;
    private String body;
    private String title;
    private String created;
    private String updated;

    public Post(String title, String body) {
        this.body = body;
        this.title = title;
    }

    @JsonCreator
    public Post() {}

    public void addLinks() {
        this.add(linkTo(methodOn(PostController.class).getAllPosts()).withSelfRel());
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /*public String getCreated() {
        return this.created;
    }*/

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