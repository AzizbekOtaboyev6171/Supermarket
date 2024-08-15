package com.example.supermarket.dto.supplierBalance;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SupplierBalanceDTO {
    Long id;
    Long supplierId;
    Double totalDebt;
    Double totalPayment;
    Timestamp updatedAt;
}