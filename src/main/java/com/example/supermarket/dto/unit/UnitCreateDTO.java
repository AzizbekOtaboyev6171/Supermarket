package com.example.supermarket.dto.unit;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "UnitCreateDTO", description = "Data Transfer Object (DTO) used for creating a new unit. This includes all necessary details required to register a new unit in the system.")
public class UnitCreateDTO {
    @NotNull(message = "Unit template ID is required")
    @Schema(description = "ID of the unit template", example = "1", required = true)
    Long unitTemplateId;
    @NotNull(message = "Accuracy is required")
    @Min(value = 0, message = "Accuracy must be at least 0")
    @Max(value = 5, message = "Accuracy must be at most 5")
    @Schema(description = "Accuracy of the unit", example = "2", required = true)
    Integer accuracy;
    @NotNull(message = "Status is required")
    @Schema(description = "Status of the unit", example = "true")
    Boolean status;
}