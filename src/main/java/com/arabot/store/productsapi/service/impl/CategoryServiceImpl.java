package com.arabot.store.productsapi.service.impl;

import com.arabot.store.productsapi.constants.ErrorMessage;
import com.arabot.store.productsapi.dto.ProductCategory;
import com.arabot.store.productsapi.dto.CategoryRequest;
import com.arabot.store.productsapi.exception.CategoryException;
import com.arabot.store.productsapi.exception.ProductException;
import com.arabot.store.productsapi.model.Category;
import com.arabot.store.productsapi.model.SubCategory;
import com.arabot.store.productsapi.repository.CategoryRepository;
import com.arabot.store.productsapi.repository.SubCategoryRepository;
import com.arabot.store.productsapi.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final ModelMapper mapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, SubCategoryRepository subCategoryRepository ,ModelMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.mapper = mapper;
    }

    @Override
    public CategoryRequest createCategory(CategoryRequest categoryRequest) {

            Category category = mapper.map(categoryRequest, Category.class);

            if (categoryRepository.findByName(category.getName()).isPresent()) {

                throw new IllegalArgumentException(ErrorMessage.CATEGORY_ALREADY_EXITS);
            }

            categoryRepository.save(category);
            return categoryRequest;

    }

    @Override
    public CategoryRequest createSubCategory(CategoryRequest categoryRequest) {

        SubCategory subCategory = mapper.map(categoryRequest, SubCategory.class);

        if (subCategoryRepository.findByName(subCategory.getName()).isPresent()) {

            throw new IllegalArgumentException(ErrorMessage.SUBCATEGORY_ALREADY_EXITS);
        }

        subCategoryRepository.save(subCategory);
        return categoryRequest;

    }

    @Override
    public Page<CategoryRequest> getAllCategories(Pageable pageable) {

        try {

            Page<Category> categoriesFounded = categoryRepository.findAll(pageable);

            return categoriesFounded.map(category -> mapper.map(category, CategoryRequest.class));


        } catch (Exception ex) {

            log.error(ErrorMessage.CATEGORY_GET_ALL_ERROR, ex);
            throw new ProductException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessage.CATEGORY_GET_ALL_ERROR, ex.getMessage());

        }
    }

    @Override
    public Page<CategoryRequest> getAllSubCategories(Pageable pageable) {

        try {

            Page<SubCategory> subCategoriesFounded = subCategoryRepository.findAll(pageable);

            return subCategoriesFounded.map(subCategory -> mapper.map(subCategory, CategoryRequest.class));


        } catch (Exception ex) {

            log.error(ErrorMessage.SUBCATEGORY_ALREADY_EXITS, ex);  // Provide context for logging exceptions
            throw new ProductException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessage.SUBCATEGORY_ALREADY_EXITS, ex.getMessage());

        }

    }

    @Override
    public void validateIfCategoryAndSubCategotyExits (ProductCategory productCategory) {

        categoryRepository.findSingleCategoryByNameStrict(productCategory.getCategory())
                .orElseThrow(() -> new CategoryException(HttpStatus.BAD_REQUEST, ErrorMessage.CATEGORY_NOT_FOUND_MESSAGE, ""));

        categoryRepository.findSingleSubCategoryByNameStrict(productCategory.getSubCategory())
                .orElseThrow(() -> new CategoryException(HttpStatus.BAD_REQUEST, ErrorMessage.SUBCATEGORY_NOT_FOUND_MESSAGE, ""));

    }
}
