package com.example.hateoasapi.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserProfile {

    private String firstName;
    private String lastName;
    private String avatarPath;

}
