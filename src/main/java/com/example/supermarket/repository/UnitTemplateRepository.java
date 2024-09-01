package com.example.supermarket.repository;

import com.example.supermarket.entity.UnitTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitTemplateRepository extends JpaRepository<UnitTemplate, Long> {
    Boolean existsByNameIgnoreCase(String name);
    Boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);
    Boolean existsBySymbolIgnoreCase(String symbol);
    Boolean existsBySymbolIgnoreCaseAndIdNot(String symbol, Long id);
    List<UnitTemplate> findAllByBaseUnitId(Long baseUnitId);
}