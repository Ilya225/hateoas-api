package com.example.hateoasapi.domain;

import javax.persistence.Id;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.hateoas.ResourceSupport;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category extends ResourceSupport {

    @Id
    private String _id;
    private String name;

    @CreatedDate
    private DateTime createdDate;

    @LastModifiedDate
    private DateTime lastModifiedDate;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }
}