package com.example.hateoasapi.service.impl;

import com.example.hateoasapi.config.JwtProperties;
import com.example.hateoasapi.domain.User;
import com.example.hateoasapi.service.TokenAuthenticationService;
import com.example.hateoasapi.utils.exception.JwtTokenException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static java.util.Collections.emptyList;

@Service
public class TokenAuthenticationServiceImpl implements TokenAuthenticationService {

    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    private UserDetailsService userDetailsService;
    private JwtProperties jwtProperties;

    public TokenAuthenticationServiceImpl(
            UserDetailsService userDetailsService,
            JwtProperties jwtProperties
    ) {
        this.userDetailsService = userDetailsService;
        this.jwtProperties = jwtProperties;
    }

    @Override
    public void addAuthentication(HttpServletResponse res, String username) {

        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpirationMillis()))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
    }

    @Override
    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        System.out.println(jwtProperties);
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpirationMillis()))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
    }

    @Override
    public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token == null) {
            throw new JwtTokenException("JWT token is missing");
        }
            // parse the token.
            String username = Jwts.parser()
                    .setSigningKey(jwtProperties.getSecret())
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

        User user = (User) userDetailsService.loadUserByUsername(username);

            if(user == null)
                throw new JwtTokenException("JWT token is invalid");

            return new UsernamePasswordAuthenticationToken(user, null, emptyList());
    }
}
