package com.example.hateoasapi.agregator.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubUserRepos {

    private String name;
    private String description;
    private boolean fork;
    private String languagesUrl;
    private String commitsUrl;
    private String language;

} 