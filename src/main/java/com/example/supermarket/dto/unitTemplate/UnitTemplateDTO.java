package com.example.supermarket.dto.unitTemplate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "UnitTemplateDTO", description = "Data Transfer Object (DTO) representing a unit template in the system.")
public class UnitTemplateDTO {
    Long id;
    String name;
    String symbol;
    Long baseUnitId;
    Integer conversionFactor;
    Timestamp createdAt;
    Timestamp updatedAt;
    Timestamp deletedAt;
}