package com.example.supermarket.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        Server server = new Server().url("https://tezsavdomarket.uz").description("Production Server");
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
                .addTagsItem(new Tag().name("Unit Template").description("Methods for Unit Template"))
                .servers(List.of(server));

    }
}
