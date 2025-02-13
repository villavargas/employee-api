package com.example.employeeapi.util;

public class Constants {
    public static final String STARTING_APP = "Starting Employee API Application...";
    public static final String APP_STARTED = "Employee API Application Started.";
    public static final String DB_DRIVER = "org.h2.Driver";
    public static final String DB_URL = "jdbc:h2:~/test";
    public static final String DB_USERNAME = "sa";
    public static final String DB_PASSWORD = "";
    public static final String LOG_FILE = "logs/application.log";
    public static final String LOG_PATTERN = "{\"time\":\"%d{yyyy-MM-dd HH:mm:ss}\",\"level\":\"%p\",\"logger\":\"%c\",\"message\":\"%m\"}%n";
    public static final String EMPLOYEE_NOT_FOUND = "Employee not found";
}