package com.example.hateoasapi.security;

import com.example.hateoasapi.service.TokenAuthenticationService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private TokenAuthenticationService tokenAuthenticationService;

    public JWTAuthenticationFilter(
            String defaultFilterProcessesUrl, 
            AuthenticationManager authenticationManager, 
            TokenAuthenticationService tokenAuthenticationService) {
        super(defaultFilterProcessesUrl);
        setAuthenticationManager(authenticationManager);
        this.tokenAuthenticationService = tokenAuthenticationService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        System.out.println();
        System.out.println("-------------------------------------------------");
        System.out.println(getAuthenticationManager());
        System.out.println("-------------------------------------------------");
        System.out.println();
        return getAuthenticationManager()
                .authenticate(tokenAuthenticationService
                        .getAuthentication(httpServletRequest)
                );
    }

   /* @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        System.out.println("Successfully authorizated");
        //chain.doFilter(request,response);
    }*/
}
