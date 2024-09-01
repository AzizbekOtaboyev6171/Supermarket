package com.example.supermarket.dto.category;

import com.example.supermarket.entity.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "CategoryDTO", description = "Data Transfer Object (DTO) representing a category in the system.")
public class CategoryDTO {
    Long id;
    String name;
    Long productCount;
    Timestamp createdAt;
    Timestamp updatedAt;
    Timestamp deletedAt;
    List<Product> productList;
}