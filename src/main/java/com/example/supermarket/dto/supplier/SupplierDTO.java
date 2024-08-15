package com.example.supermarket.dto.supplier;

import com.example.supermarket.dto.attachment.AttachmentDTO;
import com.example.supermarket.dto.supplierBalance.SupplierBalanceDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SupplierDTO {
    Long id;
    String name;
    List<String> phoneNumber;
    String contactPerson;
    String tin;
    String address;
    String bankName;
    String bankAccount;
    String bankCode;
    String note;
    Timestamp createdAt;
    Timestamp updatedAt;
    Timestamp deletedAt;
    SupplierBalanceDTO supplierBalanceDTO;
    List<AttachmentDTO> attachmentDTOList;
}