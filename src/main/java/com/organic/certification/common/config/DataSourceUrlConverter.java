package com.organic.certification.common.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DataSourceUrlConverter {

    @Value("${DATABASE_URL:}")
    private String databaseUrl;

    @PostConstruct
    public void adjustDatabaseUrl() {
        if (databaseUrl != null && databaseUrl.startsWith("postgresql://")) {
            // Convert "postgresql://" → "jdbc:postgresql://"
            String jdbcUrl = "jdbc:" + databaseUrl;

            // Set it back as an env property for Spring Boot
            System.setProperty("spring.datasource.url", jdbcUrl);
        }
    }
}
