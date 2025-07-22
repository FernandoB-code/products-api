package com.arabot.store.productsapi.controller;


import com.arabot.store.productsapi.dto.ProductDto;
import com.arabot.store.productsapi.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {

        return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.OK);

    }

    @GetMapping("/getAll")
    public ResponseEntity<Page<ProductDto>> getAllProducts(Pageable pageable) {

        return new ResponseEntity<>(productService.getAllProducts(pageable), HttpStatus.OK);

    }
}