package com.hamedTech.bilingsoftware.service;

import com.hamedTech.bilingsoftware.dto.CategoryRequest;
import com.hamedTech.bilingsoftware.dto.CategoryResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {

    CategoryResponse addCategory(CategoryRequest request, MultipartFile file);

    List<CategoryResponse> getAllCategories();

    void deleteCategory(String categoryId);
}
