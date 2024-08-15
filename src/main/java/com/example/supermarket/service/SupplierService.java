package com.example.supermarket.service;

import com.example.supermarket.dto.supplier.SupplierCreateDTO;
import com.example.supermarket.dto.supplier.SupplierDTO;
import com.example.supermarket.dto.supplier.SupplierUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface SupplierService {
    SupplierDTO create(SupplierCreateDTO supplierCreateDTO);

    SupplierDTO update(Long id, SupplierUpdateDTO supplierUpdateDTO);

    Optional<SupplierDTO> findById(Long id);

    List<SupplierDTO> searchActiveSuppliers(int page, int size, String keyword);

    List<SupplierDTO> searchInactiveSuppliers(int page, int size, String keyword);

    void deleteById(Long id);

    void restoreById(Long id);

    Boolean existsByName(String name);

    Boolean existsByNameAndId(String name, Long id);

    Boolean existsByTin(String tin);

    Boolean existsByTinAndId(String tin, Long id);
}