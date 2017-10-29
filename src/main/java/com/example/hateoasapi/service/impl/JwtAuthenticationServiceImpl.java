package com.example.hateoasapi.service.impl;

import com.example.hateoasapi.model.AccountCredentials;
import com.example.hateoasapi.model.JwtToken;
import com.example.hateoasapi.service.JwtAuthenticationService;
import com.example.hateoasapi.service.TokenAuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class JwtAuthenticationServiceImpl implements JwtAuthenticationService {

    private AuthenticationManager authenticationManager;
    private TokenAuthenticationService tokenAuthenticationService;

    public JwtAuthenticationServiceImpl(
            AuthenticationManager authenticationManager,
            TokenAuthenticationService tokenAuthenticationService
            ) {
        this.authenticationManager = authenticationManager;
        this.tokenAuthenticationService = tokenAuthenticationService;
    }

    @Override
    public Authentication login(AccountCredentials accountCredentials) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        accountCredentials.getUsername(),
                        accountCredentials.getPassword()
                ));
    }

    @Override
    public JwtToken retrieveJwtToken(AccountCredentials accountCredentials) {
        Authentication auth = login(accountCredentials);
        String token = tokenAuthenticationService.generateToken(auth);
        return new JwtToken(token);
    }
}
