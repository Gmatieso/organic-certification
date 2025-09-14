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
            String jdbcUrl = "jdbc:" + databaseUrl;
            System.setProperty("spring.datasource.url", jdbcUrl);
            System.out.println("✅ Converted DATABASE_URL to JDBC: " + jdbcUrl);
        } else {
            System.out.println("ℹ️ DATABASE_URL not set or already JDBC formatted.");
        }
    }
}
