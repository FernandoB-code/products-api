package com.arabot.store.productsapi.service;

import com.arabot.store.productsapi.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto);

    Page<ProductDto> getAllProducts(Pageable pageable);

}
