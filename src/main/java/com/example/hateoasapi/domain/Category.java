package com.example.hateoasapi.domain;

import javax.persistence.Id;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.hateoas.ResourceSupport;

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

    public String get_Id() {
        return _id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public DateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(DateTime createdDate) {
        this.createdDate = createdDate;
    }

    public DateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void getLastModifiedDate(DateTime lastModifiedDate) {
        this.lastModifiedDate  = lastModifiedDate;
    }
}