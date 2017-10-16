package com.example.hateoasapi.controller;


import com.example.hateoasapi.model.RegisterForm;
import com.example.hateoasapi.service.AccountService;
import com.example.hateoasapi.service.StorageService;
import com.example.hateoasapi.utils.exception.StorageFileNotFoundException;
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

    public UserController(
            AccountService accountService,
            StorageService storageService
    ) {
        this.accountService = accountService;
        this.storageService = storageService;
    }

    @PostMapping(path = "/register") 
    public ResponseEntity<?> createUser(@Valid @RequestBody RegisterForm input) {
        System.out.println(input);
        accountService.registerUser(input.getUsername(), input.getEmail(), input.getPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/upload-avatar")
    public ResponseEntity<?> uploadAvatar(@RequestParam("file") MultipartFile file) {

        storageService.store(file);

        return ResponseEntity.ok("Upload image path");
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