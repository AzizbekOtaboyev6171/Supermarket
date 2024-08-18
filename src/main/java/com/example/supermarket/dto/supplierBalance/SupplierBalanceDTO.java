package com.example.supermarket.dto.supplierBalance;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "SupplierBalanceDTO", description = "Data Transfer Object (DTO) used to represent the balance information of a supplier. This includes details such as the supplier's ID, current balance, and currency. It is used to display and manage the financial balance associated with a supplier.")
public class SupplierBalanceDTO {
    Long id;
    Long supplierId;
    Double totalDebt;
    Double totalPayment;
    Timestamp updatedAt;
}