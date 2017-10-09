package com.example.hateoasapi.controller;

import java.util.*;
import java.util.stream.Collectors;

import com.example.hateoasapi.domain.*;
import com.example.hateoasapi.repository.CategoryRepository;
import com.example.hateoasapi.service.PostService;
import com.example.hateoasapi.webform.PostQuery;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
@RequestMapping("/api")
@RestController
public class PostController {

    public static final int POSTS_PER_PAGE = 20;

    private PostService postService;
    private CategoryRepository categoryRepository;

    public PostController(
        PostService postService,
        CategoryRepository categoryRepository
    ) {
        this.categoryRepository = categoryRepository;
        this.postService = postService;
    }

    @RequestMapping(path="/posts", method=RequestMethod.GET)
    public HttpEntity<List<Post>> getAllPosts(PostQuery postQuery) {

        System.out.println(postQuery);
        List<Post> list = postService.queryAllPosts(postQuery, PostController.POSTS_PER_PAGE);

        return new ResponseEntity<List<Post>>(list, HttpStatus.OK);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<Post> getPost(@PathVariable String id) {
        Optional<Post> post = postService.findById(id);
        if(post.isPresent()) {
            return new ResponseEntity<Post>(post.get(), HttpStatus.OK);
        }
        
        return new ResponseEntity<>(HttpStatus.NOT_FOUND).noContent().build();
    }

    @RequestMapping(path="/post/create", method=RequestMethod.POST)
    public ResponseEntity<?> addPost(@RequestBody Post input) {
        postService.save(new Post(input.getTitle(), input.getBody()));
        return new ResponseEntity<>(HttpStatus.OK).noContent().build();
    }

    /**
     * Create Post with ref to Category
     */
    @RequestMapping(path = "/create_post", method = { RequestMethod.GET })
    public Post createPostGet(
        @RequestParam(name = "title", required= true) String title,
        @RequestParam(name = "body", required = true) String body,
        @RequestParam(name = "category_id", required = true) String categoryId, 
        @RequestParam(name = "tags[]", required = false) String[] tags
        ) {
            Optional<Category> optCategory = categoryRepository.findById(categoryId);
            Post post = new Post(title, body);
            List<PostTag> postTags = Arrays.asList(tags)
                                            .stream()
                                            .map(n -> new PostTag(n))
                                            .collect(Collectors.toList());
            optCategory.ifPresent((category) -> {
                post.setCategoryId(category.getObjectId());
                post.setTags(postTags);
                postService.save(post);
            });
            System.out.println(Arrays.asList(tags));
            return post;
    }

    @RequestMapping(path="/post/delete/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deletePost(@PathVariable String id) {
        //TODO delete by ID
        Optional<Post> optPost = postService.findById(id);
        if(optPost.isPresent()) {
            postService.delete(optPost.get());
            return new ResponseEntity<Post>(optPost.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND).noContent().build();
    }
}