package com.example.hateoasapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {

    private String message;
    private int statusCode;

    public ApiResponse() {}

    public ApiResponse(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

}
