package com.example.hateoasapi.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Getter
@Setter
@ToString
@Document
public class VerificationToken {

    @Id
    private String id;

    @DBRef
    private User user;

    private String token;
    private DateTime expired;

    public VerificationToken() {

    }

    public VerificationToken(String token, User user) {
        this.user = user;
        this.token = token;
        this.expired = new DateTime().plusDays(1);
    }

    public boolean isExpired() {
        return !this.expired.isAfter(DateTime.now());
    }
}
