package com.example.hateoasapi.service.impl;


import com.example.hateoasapi.service.TokenAuthenticationService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static java.util.Collections.emptyList;

@Service
public class TokenAuthenticationServiceImpl implements TokenAuthenticationService {

    static final long EXPIRATIONTIME = 864_000_000; // 10 days
    static final String SECRET = "ThisIsASecret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    @Override
    public void addAuthentication(HttpServletResponse res, String username) {

        /*Claims claims = Jwts.claims()
                .put("userId", )*/

        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
    }

    @Override
    public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token == null) {
            throw new RuntimeException("JWT token is missing");
        }
            // parse the token.
            String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            if(user == null)
                throw new RuntimeException("JWT token is invalid");

            return new UsernamePasswordAuthenticationToken(user, "admin", emptyList());
    }
}
