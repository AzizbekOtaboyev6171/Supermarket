package com.example.supermarket.mapper;

import com.example.supermarket.dto.unitTemplate.UnitTemplateDTO;
import com.example.supermarket.entity.UnitTemplate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UnitTemplateMapper extends EntityMapper<UnitTemplateDTO, UnitTemplate> {
    @Mapping(source = "baseUnit.id", target = "baseUnitId")
    UnitTemplateDTO toDTO(UnitTemplate unitTemplate);

    @Mapping(source = "baseUnitId", target = "baseUnit.id")
    UnitTemplate toEntity(UnitTemplateDTO unitTemplateDTO);

    List<UnitTemplateDTO> toDTOs(List<UnitTemplate> unitTemplates);

    List<UnitTemplate> toEntities(List<UnitTemplateDTO> unitTemplateDTOs);
}