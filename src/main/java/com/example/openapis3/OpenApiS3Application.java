package com.example.openapis3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OpenApiS3Application {

    public static void main(String[] args) {
        SpringApplication.run(OpenApiS3Application.class, args);
    }

}
