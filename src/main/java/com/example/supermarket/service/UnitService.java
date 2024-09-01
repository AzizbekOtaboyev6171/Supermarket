package com.example.supermarket.service;

import com.example.supermarket.dto.unit.UnitCreateDTO;
import com.example.supermarket.dto.unit.UnitDTO;
import com.example.supermarket.dto.unit.UnitUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface UnitService {
    UnitDTO createUnit(UnitCreateDTO unitCreateDTO);
    UnitDTO updateUnit(Long id, UnitUpdateDTO unitUpdateDTO);
    Optional<UnitDTO> findUnitById(Long id);
    void deleteUnitById(Long id);
    List<UnitDTO> searchActiveUnits(int page, int size, String keyword);
}