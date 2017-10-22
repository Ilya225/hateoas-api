package com.example.hateoasapi.domain;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.hateoas.ResourceSupport;
import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.io.Serializable;
import java.util.List;

import com.example.hateoasapi.controller.*;

@Getter
@Setter
@ToString
@Document
public class Post extends ResourceSupport implements Serializable {

    @Id
    @Field(value = "_id")
    private String objectId;

    @DBRef
    private List<Comment> comments;

    @DBRef
    private User author;

    @NotBlank
    private String body;

    @NotBlank
    private String title;
    
    private String categoryId;

    @NotEmpty(message = "Tags cannot be empty")
    private List<PostTag> tags;

    @CreatedDate
    private DateTime createdDate;

    @LastModifiedDate
    private DateTime lastModifiedDate;

    @CreatedBy
    private User createdBy;

    private Long views;    
    private List<PostRating> likes;
    private List<PostRating> dislikes;


    @JsonCreator
    public Post() {}

    public Post(String title, String body) {
        this.body = body;
        this.title = title;
    }

    public Post(User author, String body, String title, String categoryId, List<PostTag> tags) {
        this.author = author;
        this.body = body;
        this.title = title;
        this.categoryId = categoryId;
        this.tags = tags;
    }

    public void addLinks() {
        this.add(linkTo(methodOn(PostController.class).getAllPosts(null)).withSelfRel());
    }
}