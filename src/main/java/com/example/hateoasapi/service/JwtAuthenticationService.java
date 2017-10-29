package com.example.hateoasapi.service;


import com.example.hateoasapi.model.AccountCredentials;
import com.example.hateoasapi.model.JwtToken;
import org.springframework.security.core.Authentication;

public interface JwtAuthenticationService {

    Authentication login(AccountCredentials accountCredentials);

    JwtToken retrieveJwtToken(AccountCredentials accountCredentials);
}
