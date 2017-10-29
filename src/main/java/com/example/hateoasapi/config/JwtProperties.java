package com.example.hateoasapi.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "jwt")
@PropertySource("classpath:jwt.properties")
public class JwtProperties {

    private String secret;

    private Long expirationMillis;
}
