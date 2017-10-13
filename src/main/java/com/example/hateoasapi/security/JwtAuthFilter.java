package com.example.hateoasapi.security;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.example.hateoasapi.service.TokenAuthenticationService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthFilter extends BasicAuthenticationFilter {

    private TokenAuthenticationService tokenAuthenticationService;

    public JwtAuthFilter(
        AuthenticationManager authManager,
        TokenAuthenticationService tokenAuthenticationService
        ) {
            super(authManager);
            this.tokenAuthenticationService = tokenAuthenticationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer")) {
            chain.doFilter(req, res);
            return;
        }

        Authentication authentication = tokenAuthenticationService.getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }
}