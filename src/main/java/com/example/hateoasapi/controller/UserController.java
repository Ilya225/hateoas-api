package com.example.hateoasapi.controller;

import com.example.hateoasapi.domain.User;
import com.example.hateoasapi.domain.VerificationToken;
import com.example.hateoasapi.model.AccountCredentials;
import com.example.hateoasapi.model.ApiResponse;
import com.example.hateoasapi.model.JwtToken;
import com.example.hateoasapi.model.RegisterForm;
import com.example.hateoasapi.service.AccountService;
import com.example.hateoasapi.service.JwtAuthenticationService;
import com.example.hateoasapi.service.StorageService;
import com.example.hateoasapi.utils.event.OnUserRegisterEvent;
import com.example.hateoasapi.utils.exception.StorageFileNotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
public class UserController {

    private AccountService accountService;
    private StorageService storageService;
    private ApplicationEventPublisher eventPublisher;
    private JwtAuthenticationService jwtAuthenticationService;

    public UserController(
            AccountService accountService,
            StorageService storageService,
            ApplicationEventPublisher applicationEventPublisher,
            JwtAuthenticationService jwtAuthenticationService
    ) {
        this.accountService = accountService;
        this.storageService = storageService;
        this.eventPublisher = applicationEventPublisher;
        this.jwtAuthenticationService = jwtAuthenticationService;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody RegisterForm input) {

        User user = accountService.registerUser(input.getUsername(), input.getEmail(), input.getPassword());
        eventPublisher.publishEvent(new OnUserRegisterEvent(user));

        return new ResponseEntity<>(
                new ApiResponse("successfully registered", HttpStatus.OK.value()),
                HttpStatus.OK
        );
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody AccountCredentials accountCredentials) {
        JwtToken jwtToken = jwtAuthenticationService.retrieveJwtToken(accountCredentials);

        return new ResponseEntity<>(jwtToken, HttpStatus.OK);
    }

    @PostMapping(path = "/upload-avatar")
    public ResponseEntity<?> uploadAvatar(@RequestParam("file") MultipartFile file) {

        storageService.store(file);

        return ResponseEntity.ok("Upload image path");
    }

    @GetMapping(path = "/confirm")
    public String confirmRegistration(@RequestParam("token") String token) {
        VerificationToken verificationToken = accountService.retrieveToken(token);
        if (verificationToken.isExpired()) {
            return "Token is expired";
        }
        accountService.activateAccount(verificationToken.getUser());

        return "Success";
    }

    @GetMapping(path = "/avatars/{filename}.{extension:[a-zA-Z]{3,4}}")
    public ResponseEntity<?> loadAvatar(
            @PathVariable String filename,
            @PathVariable String extension
    ) {
        try {
            Resource file = storageService.loadAsResource(filename + "." + extension);
            return ResponseEntity.ok(file);
        } catch (StorageFileNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}