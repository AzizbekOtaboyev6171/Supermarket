package com.example.supermarket.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "CategoryCreateDTO", description = "Data Transfer Object (DTO) used for creating a new category. This includes all necessary details required to register a new category in the system.")
public class CategoryCreateDTO {
    @NotBlank(message = "Category name cannot be blank")
    String name;
}