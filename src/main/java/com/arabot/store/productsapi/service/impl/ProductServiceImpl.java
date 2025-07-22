package com.arabot.store.productsapi.service.impl;

import com.arabot.store.productsapi.constants.ErrorMessage;
import com.arabot.store.productsapi.dto.ProductDto;
import com.arabot.store.productsapi.exception.CategoryException;
import com.arabot.store.productsapi.exception.ProductException;
import com.arabot.store.productsapi.model.Product;
import com.arabot.store.productsapi.repository.ProductRepository;
import com.arabot.store.productsapi.service.CategoryService;
import com.arabot.store.productsapi.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ModelMapper mapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, ModelMapper mapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.mapper = mapper;
    }

    public ProductDto createProduct(ProductDto productDto) {

        try {

            categoryService.validateIfCategoryAndSubCategoryExits(productDto.getProductCategory());
            Product product = mapper.map(productDto, Product.class);
            productRepository.save(product);
            return productDto;

        } catch (CategoryException ex) {

            log.error(ErrorMessage.ERROR_CREATING_PRODUCT, ex);
            throw new ProductException(ex.getHttpStatus(), ex.getMessage(), "");

        } catch (Exception ex) {

            log.error(ErrorMessage.ERROR_CREATING_PRODUCT, ex);
            throw new ProductException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessage.CATEGORY_PERSISTENCE_ERROR, ex.getMessage());

        }
    }

    @Override
    public Page<ProductDto> getAllProducts(Pageable pageable) {

        try {

            Page<Product> productsFounded = productRepository.findAll(pageable);

            return productsFounded.map(products -> mapper.map(products, ProductDto.class));


        } catch (Exception ex) {

            log.error(ErrorMessage.CATEGORY_GET_ALL_ERROR, ex);
            throw new ProductException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessage.CATEGORY_PERSISTENCE_ERROR, ex.getMessage());

        }
    }
}

