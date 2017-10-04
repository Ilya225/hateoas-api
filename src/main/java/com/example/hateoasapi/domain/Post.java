package com.example.hateoasapi.domain;

import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.ResourceSupport;
import com.fasterxml.jackson.annotation.JsonCreator;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.io.Serializable;

import com.example.hateoasapi.controller.*;

@Document
public class Post extends ResourceSupport implements Serializable {

    @Id
    private String _id;
    //@DBRef
    //private Category category;
    private String body;
    private String title;

    @CreatedDate
    private Long createdDate;

    @LastModifiedDate
    private Long lastModifiedDate;

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

    public String get_Id() {
        return _id;
    }

    public void setCreatedDate(Long created) {
        this.createdDate = created;
    }

    public Long getCreatedDate() {
        return this.createdDate;
    }

    public void setLastModifiedDate(Long updated) {
        this.lastModifiedDate =  updated;
    }

    public Long getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    /*public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return this.category;
    }*/
}