package com.example.hateoasapi.config;

import com.example.hateoasapi.security.ExceptionHandlerFilter;
import com.example.hateoasapi.security.JWTLoginFilter;
import com.example.hateoasapi.security.JwtAuthFilter;
import com.example.hateoasapi.service.TokenAuthenticationService;
import com.example.hateoasapi.service.impl.UserDetailsServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private TokenAuthenticationService tokenAuthenticationService;
    private UserDetailsService userDetailsService;

    public WebSecurityConfig(
            TokenAuthenticationService tokenAuthenticationService,
            UserDetailsServiceImpl userDetailsService
    ) {
        this.tokenAuthenticationService = tokenAuthenticationService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/login", "/register", "/confirm")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new ExceptionHandlerFilter(), CsrfFilter.class)
                .addFilter(new ExceptionHandlerFilter())
                .addFilter(new JwtAuthFilter(authenticationManager(), tokenAuthenticationService))
                .addFilterBefore(new JWTLoginFilter(
                        "/login",
                        authenticationManager(),
                        tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


}