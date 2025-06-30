package com.hamedTech.bilingsoftware.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hamedTech.bilingsoftware.dto.CategoryRequest;
import com.hamedTech.bilingsoftware.dto.CategoryResponse;
import com.hamedTech.bilingsoftware.service.CategoryService;
import com.hamedTech.bilingsoftware.service.S3Service;
import com.hamedTech.bilingsoftware.service.impl.CategoryServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;



    @PostMapping("/add")
    public ResponseEntity<CategoryResponse> addCategory(@RequestPart("category") String categoryString,
                                                        @RequestPart ("file") MultipartFile file)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        CategoryRequest request = null;

        try{
            request = objectMapper.readValue(categoryString, CategoryRequest.class);
            CategoryResponse categoryResponse = categoryService.addCategory(request, file);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(categoryResponse);

        }catch(JsonProcessingException exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Exception occured while passing json"+ exception);

        }


    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories(){

        List<CategoryResponse> categories = categoryService.getAllCategories();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categories);

    }

    @DeleteMapping("delete/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String categoryId) {

        try {
            categoryService.deleteCategory(categoryId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }


}
