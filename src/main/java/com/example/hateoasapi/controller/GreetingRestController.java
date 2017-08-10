package com.example.hateoasapi.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.hateoasapi.domain.*;
import com.example.hateoasapi.repository.CustomerRepository;

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
    private CustomerRepository customerRepository;

    public static Post post = new Post("hello", "hello world", "general", new Date().toLocaleString());
    public static Post post1 = new Post("more", "more than a feeling", "general", new Date().toLocaleString());
    public static Post post2 = new Post("Just", "just a moment", "general", new Date().toLocaleString());

    public GreetingRestController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    
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
    public HttpEntity<List<Post>> posts() {
        List<Post> list = new ArrayList<>();

        list.add(post);
        list.add(post1);
        list.add(post2);

        post.add(linkTo(methodOn(GreetingRestController.class).posts()).withSelfRel());
        post1.add(linkTo(methodOn(GreetingRestController.class).posts()).withSelfRel());
        post2.add(linkTo(methodOn(GreetingRestController.class).posts()).withSelfRel());
        ResponseEntity<List<Post>> response = new ResponseEntity<List<Post>>(list, HttpStatus.OK);

        return response;
    }


    @RequestMapping(path="/post", method=RequestMethod.GET)
    public HttpEntity<Post> onePost() {
        post.add(linkTo(methodOn(GreetingRestController.class).onePost()).withSelfRel());

        return new ResponseEntity<Post>(post, HttpStatus.OK); 
    }

    @RequestMapping(path="/createCustomer", method=RequestMethod.GET)
    public HttpEntity<Customer> createCustomer(
        @RequestParam(value="firstName", required=true) String firstName,
        @RequestParam(value="lastName", required=true) String lastName
    ) {

        Customer customer = new Customer(firstName, lastName);

        customerRepository.save(customer);
        

        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @RequestMapping(path="/allCustomers", method=RequestMethod.GET)
    public HttpEntity<List<Customer>> allCustomers() {
        List<Customer> customerList = customerRepository.findAll();

        return new ResponseEntity<List<Customer>>(customerList, HttpStatus.OK);
    }
}