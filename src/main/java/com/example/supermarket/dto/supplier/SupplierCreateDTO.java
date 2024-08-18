package com.example.supermarket.dto.supplier;

import com.example.supermarket.validations.NullOrNotBlank;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "SupplierCreateDTO", description = "Data Transfer Object (DTO) used for creating a new supplier. This includes all necessary details required to register a new supplier in the system.")
public class SupplierCreateDTO {
    @NotBlank(message = "Name cannot be blank")
    String name;
    @Valid
    @NotNull(message = "Phone number cannot be null")
    @NotEmpty(message = "Phone number cannot be empty")
    List<@NotBlank(message = "Phone number cannot be blank") @Pattern(regexp = "^\\+998[0-9]{9}$", message = "Phone number must start with +998 and contain 9 digits") String> phoneNumber;
    @NullOrNotBlank(message = "ContactPerson can be null but not blank")
    String contactPerson;
    @Pattern(regexp = "^(\\d{8}|\\d{14})$", message = "INN must contain either 8 or 14 digits, using only digits 0-9")
    @NullOrNotBlank(message = "INN can be null but not blank")
    String tin;
    @NullOrNotBlank(message = "Address can be null but not blank")
    String address;
    @NullOrNotBlank(message = "BankName can be null but not blank")
    String bankName;
    @NullOrNotBlank(message = "BankAccount can be null but not blank")
    String bankAccount;
    @NullOrNotBlank(message = "BankCode can be null but not blank")
    String bankCode;
    @NullOrNotBlank(message = "Note can be null but not blank")
    String note;
    List<@NotNull(message = "Attachment ID cannot be null") Long> attachment;
}