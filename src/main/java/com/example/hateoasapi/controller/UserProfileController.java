package com.example.hateoasapi.controller;

import com.example.hateoasapi.model.UserProfileForm;
import com.example.hateoasapi.service.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping(path = "api/user/profile")
public class UserProfileController {

    private UserProfileService userProfileService;

    public UserProfileController(
            UserProfileService userProfileService
    ) {
        this.userProfileService = userProfileService;
    }

    public ResponseEntity<?> showProfile() {
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(path = "/save", method = { RequestMethod.POST })
    public ResponseEntity<?> saveProfile(@Valid UserProfileForm userProfileForm) {

        userProfileService.save(userProfileForm);

        return ResponseEntity.noContent().build();
    }
}
