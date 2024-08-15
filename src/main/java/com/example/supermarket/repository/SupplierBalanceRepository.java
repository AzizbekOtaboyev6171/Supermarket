package com.example.supermarket.repository;

import com.example.supermarket.entity.SupplierBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierBalanceRepository extends JpaRepository<SupplierBalance, Long> {
    Boolean existsBySupplierId(Long supplierId);
}