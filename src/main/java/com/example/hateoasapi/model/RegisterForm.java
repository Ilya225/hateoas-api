package com.example.hateoasapi.model;

import com.example.hateoasapi.utils.constraint.UniqueUser;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class RegisterForm {

    @Email
    @UniqueUser(message = "Email already in use")
    private String email;

    @NotBlank
    @UniqueUser(field = "username")
    private String username;

    @Size(min = 8)
    private String password;

    @Size(min = 8)
    private String confirm;

}
