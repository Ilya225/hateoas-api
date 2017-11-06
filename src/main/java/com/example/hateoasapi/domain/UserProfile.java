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
    private String phone;
    private String avatarPath;

    public UserProfile(String firstName, String lastName, String phone, String avatarPath) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.avatarPath = avatarPath;
    }

    public UserProfile() {}
}
