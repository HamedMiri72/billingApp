package com.hamedTech.bilingsoftware.controller;

import com.hamedTech.bilingsoftware.dto.CategoryRequest;
import com.hamedTech.bilingsoftware.dto.CategoryResponse;
import com.hamedTech.bilingsoftware.service.CategoryService;
import com.hamedTech.bilingsoftware.service.impl.CategoryServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @PostMapping("/add")
    public ResponseEntity<CategoryResponse> addCategory(@RequestBody CategoryRequest categoryRequest) {

        CategoryResponse categoryResponse = categoryService.addCategory(categoryRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoryResponse);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories(){

        List<CategoryResponse> categories = categoryService.getAllCategories();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categories);

    }

    @DeleteMapping("delete")
    public ResponseEntity<Void> deleteCategory(@RequestParam String categoryId){

        categoryService.deleteCategory(categoryId);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();


    }
}
