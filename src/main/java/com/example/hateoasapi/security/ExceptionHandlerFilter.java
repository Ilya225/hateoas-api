package com.example.hateoasapi.security;

import com.example.hateoasapi.model.ApiResponse;
import com.example.hateoasapi.utils.exception.JwtTokenException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);

        } catch(JwtTokenException e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setHeader("Content-type", "application/json");
            response.getWriter().write(convertObjectToJson(
                    new ApiResponse(
                            e.getMessage(),
                            HttpStatus.BAD_REQUEST.value()
                    )
            ));
        } /*catch (RuntimeException e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setHeader("Content-type", "application/json");
            response.getWriter().write(convertObjectToJson(
                    new ApiResponse(
                            e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR.value()
                    )
            ));
        }*/
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
