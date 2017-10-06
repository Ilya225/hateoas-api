package com.example.hateoasapi.domain;

import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.ResourceSupport;
import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.Getter;
import lombok.Setter;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.io.Serializable;
import java.util.List;

import com.example.hateoasapi.controller.*;

@Getter
@Setter
@Document
public class Post extends ResourceSupport implements Serializable {

    @Id
    private String _id;
    @DBRef
    private Category category;

    @DBRef
    private List<Comment> comments;

    private String body;
    private String title;
    private List<PostTag> tags;

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
}