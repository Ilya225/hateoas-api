package com.example.hateoasapi.controller;

import com.example.hateoasapi.service.LinkedInService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RequestMapping("/api/linkedin")
@RestController
public class LinkedInController {

    private static final int MAX_PARSED_USERS = 10;

    private LinkedInService linkedInService;

    public LinkedInController(
            LinkedInService linkedInService
    ) {
        this.linkedInService = linkedInService;
    }

    @RequestMapping(path="/login", method=RequestMethod.POST)
    public ResponseEntity<Map> login(
            @RequestParam(name = "login", required  = true) String login,
            @RequestParam(name = "password", required  = true) String password
    ) {
        Map result = linkedInService.login(login, password);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(path="/parseUsers", method=RequestMethod.POST)
    public ResponseEntity<Map> parseUsers(
            @RequestParam(name = "maxAmount") Integer maxAmount
    ) {
        maxAmount = maxAmount != null ? maxAmount : MAX_PARSED_USERS;
        Map result = new HashMap();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}