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
@ConfigurationProperties(prefix = "cors")
@PropertySource("classpath:cors.properties")
public class CorsProperties {

    private String[] allowedOrigins;

    private String[] allowedMethods;
}
