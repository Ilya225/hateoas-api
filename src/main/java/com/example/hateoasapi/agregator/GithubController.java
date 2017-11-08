package com.example.hateoasapi.agregator;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.hateoasapi.agregator.github.GithubUserRepos;

@RestController
@RequestMapping("/api/agregator/github")
public class GithubController {

    @RequestMapping(path = "/{username}/repos", method =  { RequestMethod.GET })
    public GithubUserRepos[] getUserRepos(@PathVariable String username) {
        RestTemplate template = new RestTemplate();
        GithubUserRepos[] userRepos = template.getForObject("https://api.github.com/users/" + username + "/repos", GithubUserRepos[].class);

        return userRepos;
    }
}