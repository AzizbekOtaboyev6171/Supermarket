package com.example.supermarket.dto.unitTemplate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "UnitTemplateCreateDTO", description = "Data Transfer Object (DTO) used for creating a new unit template. This includes all necessary details required to register a new unit template in the system.")
public class UnitTemplateCreateDTO {
    @NotBlank(message = "Name is mandatory")
    @Schema(description = "Name of the unit template.", example = "Kilogram")
    String name;
    @NotBlank(message = "Symbol is mandatory")
    @Schema(description = "Symbol of the unit template.", example = "kg")
    String symbol;
    @Schema(description = "Unique identifier of the base unit template in the system.", example = "1")
    Long baseUnitId;
    @NotNull(message = "Conversion Factor is mandatory")
    @Schema(description = "Conversion factor of the unit template.", example = "1000")
    Integer conversionFactor;
}