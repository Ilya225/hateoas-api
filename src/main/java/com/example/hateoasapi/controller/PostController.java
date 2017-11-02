package com.example.hateoasapi.controller;

import java.util.*;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.example.hateoasapi.domain.*;
import com.example.hateoasapi.repository.CategoryRepository;
import com.example.hateoasapi.service.PostService;
import com.example.hateoasapi.model.PostQuery;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> addPost(Authentication auth, @Valid @RequestBody Post input) {
        User user = (User) auth.getPrincipal();
        Post post = new Post(
                user,
                input.getTitle(),
                input.getBody(),
                input.getCategoryId(),
                input.getTags());
        postService.save(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Create Post with ref to Category
     */
    @RequestMapping(path = "/create_post", method = { RequestMethod.GET })
    public Post createPostGet(
        @RequestParam(name = "title") String title,
        @RequestParam(name = "body") String body,
        @RequestParam(name = "category_id") String categoryId,
        @RequestParam(name = "tags[]") String[] tags
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
    public ResponseEntity<?> deletePost(Authentication auth, @PathVariable String id) {

        Optional<Post> optPost = postService.findById(id);
        if(optPost.isPresent()) {
            postService.delete(optPost.get());
            return new ResponseEntity<Post>(optPost.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND).noContent().build();
    }
}