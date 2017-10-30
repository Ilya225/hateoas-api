package com.example.hateoasapi.controller;

import com.example.hateoasapi.model.UserProfileForm;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping(path = "api/user/profile")
public class UserProfileController {

    public UserProfileController() {

    }

    public ResponseEntity<?> showProfile() {
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(path = "/save", method = { RequestMethod.POST })
    public ResponseEntity<?> saveProfile(Authentication auth, @Valid UserProfileForm userProfileForm) {
        System.out.println(auth);

        System.out.println(userProfileForm);
        return ResponseEntity.noContent().build();
    }
}
