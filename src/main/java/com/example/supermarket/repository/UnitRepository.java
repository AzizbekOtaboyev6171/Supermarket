package com.example.supermarket.repository;

import com.example.supermarket.entity.Unit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {
    Boolean existsByUnitTemplateIdAndDeletedAtIsNull(Long unitTemplateId);
    Boolean existsByUnitTemplateIdAndDeletedAtIsNullAndIdNot(Long unitTemplateId, Long id);
    Optional<Unit> findByIdAndDeletedAtIsNull(Long id);
    @Query("SELECT u FROM Unit u " +
            "LEFT JOIN u.unitTemplate ut " +
            "WHERE u.deletedAt IS NULL AND " +
            "(LOWER(ut.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(ut.symbol) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<Unit> searchByKeywordAndDeletedAtIsNull(@Param("keyword") String keyword, Pageable pageable);
    @Query("SELECT u FROM Unit u WHERE u.deletedAt IS NULL")
    Page<Unit> findAllByDeletedAtIsNull(Pageable pageable);
}