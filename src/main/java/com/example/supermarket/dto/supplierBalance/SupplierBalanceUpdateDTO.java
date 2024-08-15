package com.example.supermarket.dto.supplierBalance;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SupplierBalanceUpdateDTO {
    Long supplierId;
    Double totalDebt;
    Double totalPayment;
}