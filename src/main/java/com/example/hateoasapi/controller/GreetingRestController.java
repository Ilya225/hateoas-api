package com.example.hateoasapi.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.Date;

import com.example.hateoasapi.domain.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class GreetingRestController {

    private static final String TEMPLATE = "Hello, %s!";

    @RequestMapping(path="/greeting", method=RequestMethod.GET)
    public HttpEntity<Greeting> greeting(
        @RequestParam(value="name", required=false, defaultValue="World") String name
    ) {
        Greeting greeting = new Greeting(String.format(TEMPLATE, name));
        greeting.add(linkTo(methodOn(GreetingRestController.class).greeting(name)).withSelfRel());
        ResponseEntity<Greeting> response = new ResponseEntity<Greeting>(greeting, HttpStatus.OK);

        return response;
    }

    @RequestMapping(path="/posts", method=RequestMethod.GET)
    public HttpEntity<Post> posts() {
        Post post = new Post("hello", "hello world", "general", new Date().toLocaleString());
        post.add(linkTo(methodOn(GreetingRestController.class).posts()).withSelfRel());
        ResponseEntity<Post> response = new ResponseEntity<Post>(post, HttpStatus.OK);

        return response;
    }
}