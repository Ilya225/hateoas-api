package com.example.hateoasapi.utils.exception;


public class JwtTokenException extends RuntimeException {

    public JwtTokenException(String message) {
        super(message);
    }

    public JwtTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
