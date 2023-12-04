package com.arabot.store.productsapi.controller;


import com.arabot.store.productsapi.dto.ProductRequest;
import com.arabot.store.productsapi.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")

    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductRequest productRequest) {

        return new ResponseEntity<>(productService.createProduct(productRequest), HttpStatus.OK);

    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllProducts(Pageable pageable) {

        return new ResponseEntity<>(productService.getAllProducts(pageable), HttpStatus.OK);

    }

}