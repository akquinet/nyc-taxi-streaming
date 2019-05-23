package com.akquinet.pipeline.streaming.Config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {
    @Value("${mongodb.host}")
    private String mongodbHost;

    /**
     * My <b>MongoConfig</b>.
     *
     * @see Bean
     * Initialisastion of Mongo DB connection bean, host found in application.properties file
     */
    @Bean("mongoBean")
    public MongoClient getMongoClient() {
        return new MongoClient(new ServerAddress(mongodbHost), new MongoClientOptions.Builder().build());
    }
}
