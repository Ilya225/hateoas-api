package com.example.hateoasapi.service;


import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface TokenAuthenticationService {

    void addAuthentication(HttpServletResponse res, String username);

    Authentication getAuthentication(HttpServletRequest request);
}
