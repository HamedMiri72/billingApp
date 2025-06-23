package com.hamedTech.bilingsoftware.service;

import com.hamedTech.bilingsoftware.dto.CategoryRequest;
import com.hamedTech.bilingsoftware.dto.CategoryResponse;

public interface CategoryService {

    CategoryResponse addCategory(CategoryRequest request);
}
