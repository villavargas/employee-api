package com.example.employeeapi.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.example.employeeapi.util.Constants;

@Configuration
public class DataSourceConfig {
	
	 
    @Bean
    public DataSource dataSource() {
    	 DriverManagerDataSource dataSource = new DriverManagerDataSource();
         dataSource.setDriverClassName(Constants.DB_DRIVER);
         dataSource.setUrl(Constants.DB_URL);
         dataSource.setUsername(Constants.DB_USERNAME);
         dataSource.setPassword(Constants.DB_PASSWORD);
         return dataSource;
    }

}
