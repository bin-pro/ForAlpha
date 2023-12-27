package com.example.openapis3.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI swagger(){
        Info info = new Info();
        info.title("Swagger Test")
                .version("1.0.0")
                .description("Swagger Test");
        return new OpenAPI().info(info);
    }
}
