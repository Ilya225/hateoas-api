package com.example.hateoasapi.controller;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hateoasapi.domain.User;
import com.example.hateoasapi.domain.UserRole;
import com.example.hateoasapi.repository.UserRepository;

@RestController
public class UserController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserController(
            PasswordEncoder passwordEncoder,
            UserRepository userRepository
    ) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @PostMapping(path = "/register") 
    public ResponseEntity<?> createUser(@RequestBody User user) {

        if(user.getAuthorities() == null) {
            user.setAuthorities(Arrays.asList(new UserRole[]{ new UserRole("USER")}));
        }
        String encPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encPass);

        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}