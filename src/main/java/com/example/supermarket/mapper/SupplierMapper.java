package com.example.supermarket.mapper;

import com.example.supermarket.dto.supplier.SupplierDTO;
import com.example.supermarket.dto.supplierBalance.SupplierBalanceDTO;
import com.example.supermarket.entity.Supplier;
import com.example.supermarket.entity.SupplierBalance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SupplierMapper extends EntityMapper<SupplierDTO, Supplier> {
    @Mapping(source = "supplierBalance", target = "supplierBalanceDTO")
    @Mapping(source = "attachmentList", target = "attachmentDTOList")
    SupplierDTO toDTO(Supplier supplier);

    @Mapping(source = "supplierBalanceDTO", target = "supplierBalance")
    @Mapping(source = "attachmentDTOList", target = "attachmentList")
    Supplier toEntity(SupplierDTO supplierDTO);

    List<SupplierDTO> toDTOs(List<Supplier> suppliers);

    List<Supplier> toEntities(List<SupplierDTO> supplierDTOs);

    SupplierBalanceDTO toDTO(SupplierBalance supplierBalance);

    SupplierBalance toEntity(SupplierBalanceDTO supplierBalanceDTO);
}