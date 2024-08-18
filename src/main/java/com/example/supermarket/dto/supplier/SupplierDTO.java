package com.example.supermarket.dto.supplier;

import com.example.supermarket.dto.attachment.AttachmentDTO;
import com.example.supermarket.dto.supplierBalance.SupplierBalanceDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "SupplierDTO", description = "Data Transfer Object (DTO) representing a supplier. This DTO includes essential information about a supplier, such as their name, contact details, and bank information. It also includes the supplier's balance and a list of attachments.")
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