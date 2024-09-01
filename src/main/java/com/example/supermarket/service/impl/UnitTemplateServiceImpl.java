package com.example.supermarket.service.impl;

import com.example.supermarket.dto.unitTemplate.UnitTemplateCreateDTO;
import com.example.supermarket.dto.unitTemplate.UnitTemplateDTO;
import com.example.supermarket.dto.unitTemplate.UnitTemplateUpdateDTO;
import com.example.supermarket.entity.UnitTemplate;
import com.example.supermarket.exceptions.ResourceAlreadyExistsException;
import com.example.supermarket.exceptions.ResourceNotFoundException;
import com.example.supermarket.mapper.UnitTemplateMapper;
import com.example.supermarket.repository.UnitTemplateRepository;
import com.example.supermarket.service.UnitTemplateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UnitTemplateServiceImpl implements UnitTemplateService {

    private final UnitTemplateRepository unitTemplateRepository;
    private final UnitTemplateMapper unitTemplateMapper;

    @Override
    @Transactional
    public UnitTemplateDTO createUnitTemplate(UnitTemplateCreateDTO unitTemplateCreateDTO) {
        if (unitTemplateRepository.existsByNameIgnoreCase(unitTemplateCreateDTO.getName())) {
            throw new ResourceAlreadyExistsException("Unit template with this name already exists");
        }
        if (unitTemplateRepository.existsBySymbolIgnoreCase(unitTemplateCreateDTO.getSymbol())) {
            throw new ResourceAlreadyExistsException("Unit template with this symbol already exists");
        }
        UnitTemplate unitTemplate = new UnitTemplate();
        unitTemplate.setName(unitTemplateCreateDTO.getName());
        unitTemplate.setSymbol(unitTemplateCreateDTO.getSymbol());
        if (unitTemplateCreateDTO.getBaseUnitId() != null) {
            UnitTemplate unitTemplate1 = unitTemplateRepository.findById(unitTemplateCreateDTO.getBaseUnitId())
                    .orElseThrow(() -> new ResourceNotFoundException("Base unit template not found with ID: " + unitTemplateCreateDTO.getBaseUnitId()));
            unitTemplate.setBaseUnit(unitTemplate1);
        }
        unitTemplate.setConversionFactor(unitTemplateCreateDTO.getConversionFactor());
        return unitTemplateMapper.toDTO(unitTemplateRepository.save(unitTemplate));
    }

    @Override
    @Transactional
    public UnitTemplateDTO updateUnitTemplate(Long id, UnitTemplateUpdateDTO unitTemplateUpdateDTO) {
        UnitTemplate unitTemplate = unitTemplateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unit template not found with ID: " + id));
        if (unitTemplateRepository.existsByNameIgnoreCaseAndIdNot(unitTemplateUpdateDTO.getName(), id)) {
            throw new ResourceAlreadyExistsException("Unit template with this name already exists");
        }
        if (unitTemplateRepository.existsBySymbolIgnoreCaseAndIdNot(unitTemplateUpdateDTO.getSymbol(), id)) {
            throw new ResourceAlreadyExistsException("Unit template with this symbol already exists");
        }
        unitTemplate.setName(unitTemplateUpdateDTO.getName());
        unitTemplate.setSymbol(unitTemplateUpdateDTO.getSymbol());
        if (unitTemplateUpdateDTO.getBaseUnitId() != null) {
            UnitTemplate unitTemplate1 = unitTemplateRepository.findById(unitTemplateUpdateDTO.getBaseUnitId())
                    .orElseThrow(() -> new ResourceNotFoundException("Base unit template not found with ID: " + unitTemplateUpdateDTO.getBaseUnitId()));
            unitTemplate.setBaseUnit(unitTemplate1);
        } else {
            unitTemplate.setBaseUnit(null);
        }
        unitTemplate.setConversionFactor(unitTemplateUpdateDTO.getConversionFactor());
        return unitTemplateMapper.toDTO(unitTemplateRepository.save(unitTemplate));
    }

    @Override
    public Optional<UnitTemplateDTO> findUnitTemplateById(Long id) {
        UnitTemplate unitTemplate = unitTemplateRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unit template not found with ID: " + id));
        return Optional.ofNullable(unitTemplateMapper.toDTO(unitTemplate));
    }

    @Override
    @Transactional
    public void deleteUnitTemplateById(Long id) {
        UnitTemplate unitTemplate = unitTemplateRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unit template not found with ID: " + id));
        unitTemplateRepository.findAllByBaseUnitId(id)
                .forEach(template -> {
                    template.setBaseUnit(null);
                    unitTemplateRepository.save(template);
                });
        unitTemplateRepository.delete(unitTemplate);
    }

    @Override
    public List<UnitTemplateDTO> getUnitTemplateDtoList() {
        return unitTemplateMapper.toDTOs(unitTemplateRepository.findAll());
    }
}