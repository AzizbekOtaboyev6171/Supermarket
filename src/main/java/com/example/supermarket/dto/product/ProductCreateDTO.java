package com.example.supermarket.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "ProductCreateDTO", description = "Data Transfer Object (DTO) representing the data required to create a new Product")
public class ProductCreateDTO {
    @NotBlank(message = "Product name is required")
    String name;
    @NotBlank(message = "Article is required")
    String article;
    @NotBlank(message = "Barcode is required")
    String barcode;
    @NotNull(message = "Unit ID is required")
    Long unitId;
    @NotNull(message = "Category ID is required")
    Long categoryId;
    List<Long> attachment;
    @NotNull(message = "Flexible price is required")
    Boolean flexiblePrice;
    @NotNull(message = "Supply price is required")
    BigDecimal supplyPrice;
    @NotNull(message = "Retail price is required")
    BigDecimal retailPrice;
}