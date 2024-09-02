package com.example.supermarket.service.impl;

import com.example.supermarket.dto.category.CategoryCreateDTO;
import com.example.supermarket.dto.category.CategoryDTO;
import com.example.supermarket.dto.category.CategoryUpdateDTO;
import com.example.supermarket.entity.Category;
import com.example.supermarket.exceptions.ResourceNotFoundException;
import com.example.supermarket.mapper.CategoryMapper;
import com.example.supermarket.repository.CategoryRepository;
import com.example.supermarket.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDTO createCategory(CategoryCreateDTO categoryCreateDTO) {
        if (categoryRepository.existsByNameIgnoreCaseAndDeletedAtNull(categoryCreateDTO.getName())) {
            throw new ResourceNotFoundException("Category with this name already exists");
        }
        Category category = new Category();
        category.setName(categoryCreateDTO.getName());
        return categoryMapper.toDTO(categoryRepository.save(category));
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryUpdateDTO categoryUpdateDTO) {
        Category category = categoryRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));
        if (categoryRepository.existsByNameIgnoreCaseAndIdNotAndDeletedAtNull(categoryUpdateDTO.getName(), id)) {
            throw new ResourceNotFoundException("Category with this name already exists");
        }
        category.setName(categoryUpdateDTO.getName());
        return categoryMapper.toDTO(categoryRepository.save(category));
    }

    @Override
    public Optional<CategoryDTO> findCategoryById(Long id) {
        return Optional.ofNullable(categoryRepository.findByIdAndDeletedAtIsNull(id).map(categoryMapper::toDTO).orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id)));
    }

    @Override
    public void deleteCategoryById(Long id) {
        Category category = categoryRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));
        category.setDeletedAt(Timestamp.valueOf(LocalDateTime.now()));
        categoryRepository.save(category);
    }

    @Override
    public List<CategoryDTO> searchActiveCategories(int page, int size, String keyword) {
        Pageable pageable = PageRequest.of(page, size);
        if (keyword == null || keyword.isEmpty()) {
            return categoryRepository.findAllByDeletedAtIsNull(pageable).map(categoryMapper::toDTO).toList();
        }
        return categoryRepository.searchByKeywordAndDeletedAtIsNull(keyword, pageable).map(categoryMapper::toDTO).toList();
    }

    @Override
    public Boolean existsByName(String name) {
        return categoryRepository.existsByNameIgnoreCaseAndDeletedAtNull(name);
    }

    @Override
    public Boolean existsByNameAndId(String name, Long id) {
        return categoryRepository.existsByNameIgnoreCaseAndIdNotAndDeletedAtNull(name, id);
    }
}