package com.example.hateoasapi.utils.event;


import com.example.hateoasapi.domain.User;
import org.springframework.context.ApplicationEvent;

public class OnUserRegisterEvent extends ApplicationEvent {

    private User user;

    public OnUserRegisterEvent(User user) {
        super(user);

        this.user = user;
    }

    public User getUser() {
        return this.user;
    }
}
