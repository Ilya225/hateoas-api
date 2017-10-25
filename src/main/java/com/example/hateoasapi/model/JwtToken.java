package com.example.hateoasapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }
}
