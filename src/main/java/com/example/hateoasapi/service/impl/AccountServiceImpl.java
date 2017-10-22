package com.example.hateoasapi.service.impl;

import com.example.hateoasapi.domain.User;
import com.example.hateoasapi.domain.UserRole;
import com.example.hateoasapi.domain.VerificationToken;
import com.example.hateoasapi.repository.UserRepository;
import com.example.hateoasapi.repository.VerificationTokenRepository;
import com.example.hateoasapi.service.AccountService;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private VerificationTokenRepository verificationTokenRepository;

    public AccountServiceImpl(
            UserRepository userRepository,
            VerificationTokenRepository verificationTokenRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(String username, String email, String password) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setAuthorities(Arrays.asList(new UserRole[]{new UserRole("USER")}));

        String encPass = passwordEncoder.encode(password);
        user.setPassword(encPass);
        user.setEnabled(false);
        user.setLocked(false);

        userRepository.save(user);
        return userRepository.findByUsername(user.getUsername());
    }

    @Override
    public String generateToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public VerificationToken createVerificationToken(User user) {
        String token = generateToken();
        VerificationToken verificationToken =
                new VerificationToken(token, user);
        verificationTokenRepository.save(verificationToken);

        return verificationToken;
    }

    @Override
    public VerificationToken retrieveToken(String token) {
        Optional<VerificationToken> tokenOptional =
                verificationTokenRepository.findByToken(token);

        return tokenOptional.orElseThrow(() -> new HttpMessageNotReadableException("No token was found"));
    }

    @Override
    public void activateAccount(User user) {
        user.setEnabled(true);
        userRepository.save(user);
    }
}
