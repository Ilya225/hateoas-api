package com.example.hateoasapi.domain;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostTag {

    private String tag;

    public PostTag(String tag) {
        this.tag = tag;
    }
}