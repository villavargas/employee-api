package com.example.employeeapi.config;

import org.springframework.context.annotation.Configuration;

import com.example.employeeapi.util.Constants;

@Configuration
class LoggingConfig {
    static {
        System.setProperty("logging.file.name", Constants.LOG_FILE);
        System.setProperty("logging.pattern.console", Constants.LOG_PATTERN);
        System.setProperty("logging.pattern.file", Constants.LOG_PATTERN);
    }
}