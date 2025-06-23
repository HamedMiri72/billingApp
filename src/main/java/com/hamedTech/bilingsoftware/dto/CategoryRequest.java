package com.hamedTech.bilingsoftware.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CategoryRequest {

    private String name;

    private String description;

    private String bgColor;
}
