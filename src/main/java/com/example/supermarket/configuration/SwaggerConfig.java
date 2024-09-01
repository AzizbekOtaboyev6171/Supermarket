package com.example.supermarket.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Supermarket API")
                        .description("This is a sample Spring Boot RESTful service leveraging springdoc-openapi with OpenAPI 3 for API documentation")
                        .version("v1.0"))
                .addTagsItem(new Tag().name("Supplier").description("Methods for Supplier"))
                .addTagsItem(new Tag().name("Attachment").description("Methods for Attachment"))
                .addTagsItem(new Tag().name("Unit").description("Methods for Unit"))
                .addTagsItem(new Tag().name("Category").description("Methods for Category"))
                .addTagsItem(new Tag().name("Product").description("Methods for Product"))
                .addTagsItem(new Tag().name("Product Price History").description("Methods for Product Price History"))
                .addTagsItem(new Tag().name("Unit Template").description("Methods for Unit Template"));
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://13.250.45.188:8080")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}