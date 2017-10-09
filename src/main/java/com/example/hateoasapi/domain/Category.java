package com.example.hateoasapi.domain;

import javax.persistence.Id;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Category extends ResourceSupport {

    @Id
    @Field(value = "_id")
    private String objectId;
    private String name;

    @CreatedDate
    private DateTime createdDate;

    @LastModifiedDate
    private DateTime lastModifiedDate;

    @JsonCreator
    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }
}