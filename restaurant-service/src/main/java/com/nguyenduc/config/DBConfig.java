package com.nguyenduc.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class DBConfig {

    private final JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        try {
            jdbcTemplate.execute("SELECT 1");
            log.debug("Database connection to schema [restaurant] is successful!!");
        } catch (Exception ex) {
            log.error("Failed to connect to the database POSTGRES with schema: [restaurant]");
        }
    }

}
