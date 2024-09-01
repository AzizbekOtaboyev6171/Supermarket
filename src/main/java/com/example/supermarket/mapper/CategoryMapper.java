package com.example.supermarket.mapper;

import com.example.supermarket.dto.category.CategoryDTO;
import com.example.supermarket.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends EntityMapper<CategoryDTO, Category> {
}