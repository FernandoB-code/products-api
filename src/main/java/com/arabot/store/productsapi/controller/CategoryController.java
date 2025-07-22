package com.arabot.store.productsapi.controller;

import com.arabot.store.productsapi.dto.CategoryRequest;
import com.arabot.store.productsapi.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@Validated
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping("/category/create")
    public ResponseEntity<Object> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {

        return new ResponseEntity<>(categoryService.createCategory(categoryRequest), HttpStatus.OK);
    }

    @GetMapping("/category/getAll")
    public ResponseEntity<Object> getAllCategories(Pageable pageable) {

        return new ResponseEntity<>(categoryService.getAllCategories(pageable), HttpStatus.OK);
    }

    @PostMapping("/subcategory/create")
    public ResponseEntity<Object> createSubcategory(@Valid @RequestBody CategoryRequest categoryRequest) {

        return new ResponseEntity<>(categoryService.createSubCategory(categoryRequest), HttpStatus.OK);
    }

    @GetMapping("/subcategory/getAll")
    public ResponseEntity<Object> getAllSubCategories(Pageable pageable) {

        return new ResponseEntity<>(categoryService.getAllSubCategories(pageable), HttpStatus.OK);
    }
}