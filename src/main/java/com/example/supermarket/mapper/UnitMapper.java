package com.example.supermarket.mapper;

import com.example.supermarket.dto.unit.UnitDTO;
import com.example.supermarket.entity.Unit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UnitMapper extends EntityMapper<UnitDTO, Unit> {
}