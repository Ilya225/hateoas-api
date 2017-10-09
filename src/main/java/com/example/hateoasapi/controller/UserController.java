package com.example.hateoasapi.controller;

import java.util.Arrays;
import java.util.Collections;

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

    public UserController(
        UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    @PostMapping(path = "/register") 
    public void createUser(@RequestBody User user) {

        if(user.getAuthorities() == null) {
            user.setAuthorities(Arrays.asList(new UserRole[]{ new UserRole("USER")}));
        }
        userRepository.save(user);
        System.out.println(user);
    }
}