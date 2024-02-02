package com.arabot.store.productsapi.service;

import com.arabot.store.productsapi.dto.ProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    ProductRequest createProduct(ProductRequest productRequest);

    Page<ProductRequest> getAllProducts(Pageable pageable);

    }
