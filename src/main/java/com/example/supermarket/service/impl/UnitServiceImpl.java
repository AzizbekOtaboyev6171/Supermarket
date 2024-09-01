package com.example.supermarket.service.impl;

import com.example.supermarket.dto.unit.UnitCreateDTO;
import com.example.supermarket.dto.unit.UnitDTO;
import com.example.supermarket.dto.unit.UnitUpdateDTO;
import com.example.supermarket.entity.Unit;
import com.example.supermarket.entity.UnitTemplate;
import com.example.supermarket.exceptions.ResourceAlreadyExistsException;
import com.example.supermarket.exceptions.ResourceConflictException;
import com.example.supermarket.exceptions.ResourceNotFoundException;
import com.example.supermarket.mapper.UnitMapper;
import com.example.supermarket.repository.UnitRepository;
import com.example.supermarket.repository.UnitTemplateRepository;
import com.example.supermarket.service.UnitService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UnitServiceImpl implements UnitService {

    private final UnitRepository unitRepository;
    private final UnitTemplateRepository unitTemplateRepository;
    private final UnitMapper unitMapper;

    @Override
    @Transactional
    public UnitDTO createUnit(UnitCreateDTO unitCreateDTO) {
        UnitTemplate unitTemplate = unitTemplateRepository.findById(unitCreateDTO.getUnitTemplateId())
                .orElseThrow(() -> new ResourceNotFoundException("Unit template not found with ID: " + unitCreateDTO.getUnitTemplateId()));
        if (unitRepository.existsByUnitTemplateIdAndDeletedAtIsNull(unitCreateDTO.getUnitTemplateId())) {
            throw new ResourceAlreadyExistsException("Unit already exists with unit template ID: " + unitCreateDTO.getUnitTemplateId());
        }
        Unit unit = new Unit();
        unit.setUnitTemplate(unitTemplate);
        unit.setAccuracy(unitCreateDTO.getAccuracy());
        unit.setStatus(unitCreateDTO.getStatus());
        return unitMapper.toDTO(unitRepository.save(unit));
    }

    @Override
    @Transactional
    public UnitDTO updateUnit(Long id, UnitUpdateDTO unitUpdateDTO) {
        Unit unit = unitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unit not found with ID: " + id));
        UnitTemplate unitTemplate = unitTemplateRepository.findById(unitUpdateDTO.getUnitTemplateId())
                .orElseThrow(() -> new ResourceNotFoundException("Unit template not found with ID: " + unitUpdateDTO.getUnitTemplateId()));
        if (unitRepository.existsByUnitTemplateIdAndDeletedAtIsNullAndIdNot(unitUpdateDTO.getUnitTemplateId(), id)) {
            throw new ResourceAlreadyExistsException("Unit already exists with unit template ID: " + unitUpdateDTO.getUnitTemplateId());
        }
        unit.setUnitTemplate(unitTemplate);
        unit.setAccuracy(unitUpdateDTO.getAccuracy());
        unit.setStatus(unitUpdateDTO.getStatus());
        return unitMapper.toDTO(unitRepository.save(unit));
    }

    @Override
    public Optional<UnitDTO> findUnitById(Long id) {
        Unit unit = unitRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unit not found with ID: " + id));
        return Optional.ofNullable(unitMapper.toDTO(unit));
    }

    @Override
    public void deleteUnitById(Long id) {
        Unit unit = unitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unit not found with ID: " + id));
        if (unit.getDeletedAt() != null) {
            throw new ResourceConflictException("Unit already deleted with ID: " + id);
        }
        unit.setDeletedAt(Timestamp.valueOf(LocalDateTime.now()));
        unitRepository.save(unit);
    }

    @Override
    public List<UnitDTO> searchActiveUnits(int page, int size, String keyword) {
        PageRequest pageRequest = PageRequest.of(page, size);
        if (keyword == null || keyword.isBlank()) {
            return unitRepository.findAllByDeletedAtIsNull(pageRequest).map(unitMapper::toDTO).toList();
        }
        return unitRepository.searchByKeywordAndDeletedAtIsNull(keyword, pageRequest).map(unitMapper::toDTO).toList();
    }
}