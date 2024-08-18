package com.example.supermarket.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Supermarket API")
                        .description("This is a sample Spring Boot RESTful service leveraging springdoc-openapi with OpenAPI 3 for API documentation")
                        .version("v1.0"))
                .addTagsItem(new Tag().name("Supplier").description("Methods for Supplier"))
                .addTagsItem(new Tag().name("Attachment").description("Methods for Attachment"));
    }
}