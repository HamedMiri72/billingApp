package com.hamedTech.bilingsoftware.service.impl;

import com.hamedTech.bilingsoftware.dto.CategoryRequest;
import com.hamedTech.bilingsoftware.dto.CategoryResponse;
import com.hamedTech.bilingsoftware.entity.CategoryEntity;
import com.hamedTech.bilingsoftware.mapper.CategoryMapper;
import com.hamedTech.bilingsoftware.repository.CategoryRepository;
import com.hamedTech.bilingsoftware.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;


    @Override
    public CategoryResponse addCategory(CategoryRequest request) {

        CategoryEntity newCategory = categoryRepository.save(CategoryMapper.convertToCategoryEntity(request));

        CategoryResponse categoryResponse = CategoryMapper.convertToCategoryResponse(newCategory);

        return categoryResponse;
    }

    @Override
    public List<CategoryResponse> getAllCategories() {

        return categoryRepository.findAll()
                .stream()
                .map(categoryEntity -> CategoryMapper.convertToCategoryResponse(categoryEntity))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(String categoryId) {

        CategoryEntity existingCategory = categoryRepository.findCategoryEntitiesByCategoryIdIs(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        categoryRepository.delete(existingCategory);


    }
}
