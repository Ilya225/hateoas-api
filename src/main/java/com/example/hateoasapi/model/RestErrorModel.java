package com.example.hateoasapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestErrorModel {

    private String message;
    private int statusCode;

    public RestErrorModel() {}

    public RestErrorModel(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

}
