package com.example.hateoasapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @RequestMapping(path = "/create", method = { RequestMethod.POST })
    public void create() {

    }
}