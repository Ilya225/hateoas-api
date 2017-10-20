package com.example.hateoasapi.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@Getter
@Setter
@ToString
@Document
public class VerificationToken {

    @Id
    @Field("_id")
    private Long objectId;

    private String token;
    private DateTime expired;
}
