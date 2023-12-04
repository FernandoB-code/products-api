package com.arabot.store.productsapi.service.impl;
import com.arabot.store.productsapi.constants.ErrorMessage;
import com.arabot.store.productsapi.dto.ProductRequest;
import com.arabot.store.productsapi.exception.CategoryException;
import com.arabot.store.productsapi.exception.ProductException;
import com.arabot.store.productsapi.model.Product;
import com.arabot.store.productsapi.repository.ProductRepository;
import com.arabot.store.productsapi.service.CategoryService;
import com.arabot.store.productsapi.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryService categoryService;
    private final ModelMapper mapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, ModelMapper mapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.mapper = mapper;
    }

    public ProductRequest createProduct(ProductRequest productRequest) {

        try {

            productRequest.setId(UUID.randomUUID());

            categoryService.validateIfCategoryAndSubCategotyExits(productRequest.getProductCategory());

            Product product = mapper.map(productRequest, Product.class);
            productRepository.save(product);
            return productRequest;

        } catch (Exception ex) {

            if (ex instanceof CategoryException) {

            throw new ProductException(((CategoryException) ex).getHttpStatus(), ex.getMessage(), "");

            }

            throw new ProductException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessage.CATEGORY_PERSISTENCE_ERROR, ex.getMessage());

        }
    }

    @Override
    public Page<ProductRequest> getAllProducts(Pageable pageable) {

        try {

            Page<Product> productsFounded = productRepository.findAll(pageable);

            return productsFounded.map(products -> mapper.map(products, ProductRequest.class));




        } catch (Exception ex) {

            throw new ProductException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessage.CATEGORY_PERSISTENCE_ERROR, ex.getMessage());

        }
    }
}
