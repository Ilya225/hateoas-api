package com.example.hateoasapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfiguration {

    @Value("${spring.http.multipart.location}")
    private String uploadsPath;

    public String getUploadsPath() {
        return uploadsPath;
    }
}
