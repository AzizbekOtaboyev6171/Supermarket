package com.example.supermarket.dto.unit;

import com.example.supermarket.entity.UnitTemplate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "UnitDTO", description = "Data Transfer Object (DTO) used for returning unit details. This includes all details about the unit.")
public class UnitDTO {
    Long id;
    UnitTemplate unitTemplate;
    Integer accuracy;
    Boolean status;
    Timestamp createdAt;
    Timestamp updatedAt;
    Timestamp deletedAt;
}