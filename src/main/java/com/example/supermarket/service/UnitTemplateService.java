package com.example.supermarket.service;

import com.example.supermarket.dto.unitTemplate.UnitTemplateCreateDTO;
import com.example.supermarket.dto.unitTemplate.UnitTemplateDTO;
import com.example.supermarket.dto.unitTemplate.UnitTemplateUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface UnitTemplateService {
    UnitTemplateDTO createUnitTemplate(UnitTemplateCreateDTO unitTemplateCreateDTO);
    UnitTemplateDTO updateUnitTemplate(Long id, UnitTemplateUpdateDTO unitTemplateUpdateDTO);
    Optional<UnitTemplateDTO> findUnitTemplateById(Long id);
    void deleteUnitTemplateById(Long id);
    List<UnitTemplateDTO> getUnitTemplateDtoList();
}