package com.example.hateoasapi.domain;

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