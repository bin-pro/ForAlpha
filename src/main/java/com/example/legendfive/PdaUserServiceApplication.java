package com.example.legendfive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PdaUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PdaUserServiceApplication.class, args);
	}

}
