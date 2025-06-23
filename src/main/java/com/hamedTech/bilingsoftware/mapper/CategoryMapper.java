package com.hamedTech.bilingsoftware.mapper;

import com.hamedTech.bilingsoftware.dto.CategoryRequest;
import com.hamedTech.bilingsoftware.dto.CategoryResponse;
import com.hamedTech.bilingsoftware.entity.CategoryEntity;

import java.util.UUID;

public class CategoryMapper {

    public static CategoryEntity convertToCategoryEntity(CategoryRequest request) {

        return CategoryEntity.builder()
                .categoryId(UUID.randomUUID().toString())
                .name(request.getName())
                .description(request.getDescription())
                .bgColor(request.getBgColor())
                .build();
    }


    public static CategoryResponse convertToCategoryResponse(CategoryEntity categoryEntity){

        return CategoryResponse.builder()
                .name(categoryEntity.getName())
                .description(categoryEntity.getDescription())
                .bgColor(categoryEntity.getBgColor())
                .categoryId(categoryEntity.getCategoryId())
                .imageUrl(categoryEntity.getImageUrl())
                .createdAt(categoryEntity.getCreatedAt())
                .updatedAt(categoryEntity.getUpdatedAt())
                .build();
    }
}
