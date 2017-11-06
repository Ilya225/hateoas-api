package com.example.hateoasapi.service;

import com.example.hateoasapi.domain.User;
import com.example.hateoasapi.domain.VerificationToken;

public interface AccountService {

    User registerUser(String username, String email, String password);

    String generateToken();

    VerificationToken createVerificationToken(User user);

    VerificationToken retrieveToken(String token);

    void activateAccount(User user);
}
