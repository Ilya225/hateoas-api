package com.example.hateoasapi.controller;

import com.example.hateoasapi.service.WebDriverService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api")
@RestController
public class WebDriverController {

    private WebDriverService webDriverService;

    public WebDriverController(
            WebDriverService webDriverService
    ) {
        this.webDriverService = webDriverService;
    }

    @RequestMapping(path="/run_driver", method=RequestMethod.GET)
    public ResponseEntity<List> runDriver(
            @RequestParam(name = "query", required  = true) String query
    ) {
        List result = webDriverService.getSearchResults(query);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}