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
}
