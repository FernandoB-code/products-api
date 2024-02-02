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

    @Autowired
    CategoryService categoryService;

    @PostMapping("/category/create")

    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {

        return new ResponseEntity<>(categoryService.createCategory(categoryRequest), HttpStatus.OK);

    }

    @GetMapping("/category/getAll")
    public ResponseEntity<?> getAllCategories(Pageable pageable) {

        return new ResponseEntity<>(categoryService.getAllCategories(pageable), HttpStatus.OK);

    }

    @PostMapping("/subcategory/create")

    public ResponseEntity<?> createSubcategory(@Valid @RequestBody CategoryRequest categoryRequest) {

        return new ResponseEntity<>(categoryService.createSubCategory(categoryRequest), HttpStatus.OK);

    }

    @GetMapping("/subcategory/getAll")
    public ResponseEntity<?> getAllSubCategories(Pageable pageable) {

        return new ResponseEntity<>(categoryService.getAllSubCategories(pageable), HttpStatus.OK);

    }

}