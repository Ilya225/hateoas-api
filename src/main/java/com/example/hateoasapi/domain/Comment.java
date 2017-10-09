package com.example.hateoasapi.domain;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class Comment {

    @Id
    private String _id;
    private String message;

    @DBRef
    private Post post;

    
}