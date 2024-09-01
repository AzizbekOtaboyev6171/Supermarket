package com.example.supermarket.service;

import com.example.supermarket.dto.category.CategoryCreateDTO;
import com.example.supermarket.dto.category.CategoryDTO;
import com.example.supermarket.dto.category.CategoryUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    CategoryDTO createCategory(CategoryCreateDTO categoryCreateDTO);
    CategoryDTO updateCategory(Long id, CategoryUpdateDTO categoryUpdateDTO);
    Optional<CategoryDTO> findCategoryById(Long id);
    void deleteCategoryById(Long id);
    List<CategoryDTO> searchActiveCategories(int page, int size, String keyword);
}