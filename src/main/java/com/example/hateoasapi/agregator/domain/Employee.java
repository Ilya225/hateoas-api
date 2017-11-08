package com.example.hateoasapi.agregator.domain;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class Employee {
    
    @Id
    private String _id;

    private int age;
    private String Name;
    private String lastName;
    private String position;
}