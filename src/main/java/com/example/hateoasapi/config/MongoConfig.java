package com.example.hateoasapi.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collection;
import java.util.Collections;


@Configuration
//@EnableMongoAuditing
@EnableMongoRepositories(value = "com.example.hateoasapi.repository")
public class MongoConfig extends AbstractMongoConfiguration {

    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    @Value("${spring.data.mongodb.host}")
    private String databaseHost;

    @Value("${spring.data.mongodb.port}")
    private Integer databasePort;

    @Override
    protected String getDatabaseName() {
        return this.databaseName;
    }

    @Bean
    @Override
    public MongoClient mongoClient() {
        return new MongoClient(databaseHost, databasePort);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), databaseName);
    }

    @Override
    protected Collection<String> getMappingBasePackages() {
        return Collections.singleton("com.example.hateoasapi.domain");
    }
}