package com.example.hateoasapi.webform;

import java.util.List;

import com.example.hateoasapi.domain.PostTag;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostQuery {
    
    private String categoryId;
    private String title;
    private String body;
    private List<String> tags;
    private Integer page;

    public PostQuery() {}

}