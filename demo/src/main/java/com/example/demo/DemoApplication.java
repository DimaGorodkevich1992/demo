package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class DemoApplication {

    public static void main(final String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

}
