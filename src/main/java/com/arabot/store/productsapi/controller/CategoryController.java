package com.arabot.store.productsapi.controller;

import com.arabot.store.productsapi.dto.CategoryDTO;
import com.arabot.store.productsapi.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {

        return new ResponseEntity<>(categoryService.createCategory(categoryDTO), HttpStatus.OK);
    }

    @GetMapping("/category/getAll")
    public ResponseEntity<Page<CategoryDTO>> getAllCategories(Pageable pageable) {

        return new ResponseEntity<>(categoryService.getAllCategories(pageable), HttpStatus.OK);
    }

    @PostMapping("/subcategory/create")
    public ResponseEntity<CategoryDTO> createSubcategory(@Valid @RequestBody CategoryDTO categoryDTO) {

        return new ResponseEntity<>(categoryService.createSubCategory(categoryDTO), HttpStatus.OK);
    }

    @GetMapping("/subcategory/getAll")
    public ResponseEntity<Page<CategoryDTO>> getAllSubCategories(Pageable pageable) {

        return new ResponseEntity<>(categoryService.getAllSubCategories(pageable), HttpStatus.OK);
    }
}