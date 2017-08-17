package com.example.hateoasapi.controller;

import java.util.*;
import com.example.hateoasapi.domain.*;
import com.example.hateoasapi.repository.PostRepository;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
@RestController
public class PostController {

    private PostRepository postRepository;
    public PostController(
        PostRepository postRepository
    ) {
        this.postRepository = postRepository;
    }

    @RequestMapping(path="/posts", method=RequestMethod.GET)
    public HttpEntity<List<Post>> getAllPosts() {
        List<Post> list = postRepository.findAll();

        ResponseEntity<List<Post>> response = new ResponseEntity<List<Post>>(list, HttpStatus.OK);

        return response;
    }

    @GetMapping("/post/{id}")
    public Post getPost(@PathVariable String id) {
        Optional<Post> post = postRepository.findById(id);
        return post.get(); //TODO Optional instance handling
    }

    @RequestMapping(path="/post/create", method=RequestMethod.POST)
    public ResponseEntity<?> addPost(@RequestBody Post input) {
        postRepository.save(new Post(input.getTitle(), input.getBody()));
        return new ResponseEntity<>(HttpStatus.OK).noContent().build();
    }
}