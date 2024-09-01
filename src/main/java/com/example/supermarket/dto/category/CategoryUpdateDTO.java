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
@Schema(name = "CategoryUpdateDTO", description = "Data Transfer Object (DTO) used for updating an existing category. This includes all necessary details required to update an existing category in the system.")
public class CategoryUpdateDTO {
    @NotBlank(message = "Category name cannot be blank")
    String name;
}