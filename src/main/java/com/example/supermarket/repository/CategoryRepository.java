package com.example.supermarket.repository;

import com.example.supermarket.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Boolean existsByNameIgnoreCaseAndDeletedAtNull(String name);
    Boolean existsByNameIgnoreCaseAndIdNotAndDeletedAtNull(String name, Long id);
    Optional<Category> findByIdAndDeletedAtIsNull(Long id);
    Integer countAllByDeletedAtIsNull();
    @Query("SELECT c FROM Category c WHERE c.deletedAt IS NULL AND " +
            "(LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "ORDER BY c.id ASC")
    Page<Category> searchByKeywordAndDeletedAtIsNull(@Param("keyword") String keyword, Pageable pageable);
    @Query("SELECT c FROM Category c WHERE c.deletedAt IS NULL " +
            "ORDER BY c.id ASC")
    Page<Category> findAllByDeletedAtIsNull(Pageable pageable);
}