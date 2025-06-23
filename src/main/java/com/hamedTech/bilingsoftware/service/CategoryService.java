package com.hamedTech.bilingsoftware.service;

import com.hamedTech.bilingsoftware.dto.CategoryRequest;
import com.hamedTech.bilingsoftware.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse addCategory(CategoryRequest request);

    List<CategoryResponse> getAllCategories();

    void deleteCategory(String categoryId);
}
