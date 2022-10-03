package com.example.demo;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public DozerBeanMapper mapper() {
        return new DozerBeanMapper();
    }
}
