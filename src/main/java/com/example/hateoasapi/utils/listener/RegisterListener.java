package com.example.hateoasapi.utils.listener;

import com.example.hateoasapi.controller.UserController;
import com.example.hateoasapi.domain.User;
import com.example.hateoasapi.domain.VerificationToken;
import com.example.hateoasapi.service.AccountService;
import com.example.hateoasapi.service.EmailService;
import com.example.hateoasapi.utils.event.OnUserRegisterEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

@Component
public class RegisterListener implements ApplicationListener<OnUserRegisterEvent> {

    private EmailService emailService;
    private AccountService accountService;

    RegisterListener(
            EmailService emailService,
            AccountService accountService
    ) {
        this.emailService = emailService;
        this.accountService = accountService;
    }

    @Override
    public void onApplicationEvent(OnUserRegisterEvent event) {
        User user = event.getUser();
        VerificationToken verificationToken = accountService.createVerificationToken(user);
        UriComponents uriComponents = MvcUriComponentsBuilder
                .fromMethodName(UserController.class, "confirmRegistration", verificationToken.getToken()).build();
        String uri = uriComponents.encode().toUriString();

        emailService.sendSimpleMessage(user.getEmail(), "Confirm Registration", "<a href=\"" + uri + "\">" + uri + "</a>");
    }
}
