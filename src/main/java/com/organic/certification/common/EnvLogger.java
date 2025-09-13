package com.organic.certification.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class EnvLogger implements CommandLineRunner {

    @Value("${DATABASE_URL:NOT_SET}")
    private String databaseUrl;

    @Value("${DATABASE_USER:NOT_SET}")
    private String databaseUser;

    @Override
    public void run(String... args) {
        System.out.println(">>> DATABASE_URL = " + databaseUrl);
        System.out.println(">>> DATABASE_USER = " + databaseUser);
    }
}
