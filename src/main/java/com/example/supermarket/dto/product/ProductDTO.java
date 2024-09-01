package com.example.supermarket.dto.product;

import com.example.supermarket.entity.Attachment;
import com.example.supermarket.entity.Category;
import com.example.supermarket.entity.ProductPriceHistory;
import com.example.supermarket.entity.Unit;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "ProductCreateDTO", description = "Data Transfer Object (DTO) representing the data required to create a new Product")
public class ProductDTO {
    Long id;
    String name;
    String article;
    String barcode;
    Unit unit;
    Category category;
    List<Attachment> attachmentList;
    Boolean flexiblePrice;
    BigDecimal storeAmount;
    BigDecimal warehouseAmount;
    List<ProductPriceHistory> productPriceHistoryList;
    Timestamp createdAt;
    Timestamp updatedAt;
    Timestamp deletedAt;
}