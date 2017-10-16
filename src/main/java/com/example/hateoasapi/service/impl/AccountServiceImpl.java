package com.example.hateoasapi.service.impl;

import com.example.hateoasapi.domain.User;
import com.example.hateoasapi.domain.UserRole;
import com.example.hateoasapi.repository.UserRepository;
import com.example.hateoasapi.service.AccountService;
import com.example.hateoasapi.service.EmailService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AccountServiceImpl implements AccountService {

    private EmailService emailService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public AccountServiceImpl(
            EmailService emailService,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(String username, String email, String password) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setAuthorities(Arrays.asList(new UserRole[]{ new UserRole("USER")}));

        String encPass = passwordEncoder.encode(password);
        user.setPassword(encPass);
        user.setEnabled(false);
        user.setLocked(false);

        userRepository.save(user);
        emailService.sendSimpleMessage(email, "Welcome", "confirm your email");
    }
}
