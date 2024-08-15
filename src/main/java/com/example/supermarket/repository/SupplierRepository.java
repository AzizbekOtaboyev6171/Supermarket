package com.example.supermarket.repository;

import com.example.supermarket.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Boolean existsByName(String name);

    Boolean existsByTin(String tin);

    Boolean existsByNameAndIdNot(String name, Long id);

    Boolean existsByTinAndIdNot(String tin, Long id);

    @Query("SELECT s FROM Supplier s LEFT JOIN s.phoneNumber p " +
            "WHERE s.deletedAt IS NULL AND " +
            "(LOWER(s.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(s.contactPerson) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(p) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<Supplier> searchByKeywordAndDeletedAtIsNull(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT s FROM Supplier s WHERE s.deletedAt IS NULL")
    Page<Supplier> findAllByDeletedAtIsNull(Pageable pageable);

    @Query("SELECT s FROM Supplier s LEFT JOIN s.phoneNumber p " +
            "WHERE s.deletedAt IS NOT NULL AND " +
            "(LOWER(s.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(s.contactPerson) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(p) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<Supplier> searchByKeywordAndDeletedAtIsNotNull(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT s FROM Supplier s WHERE s.deletedAt IS NOT NULL")
    Page<Supplier> findAllByDeletedAtIsNotNull(Pageable pageable);
}