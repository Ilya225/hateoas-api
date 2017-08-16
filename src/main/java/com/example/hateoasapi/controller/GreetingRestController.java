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