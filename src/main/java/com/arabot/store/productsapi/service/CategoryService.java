package com.arabot.store.productsapi.service;

import com.arabot.store.productsapi.dto.ProductCategory;
import com.arabot.store.productsapi.dto.CategoryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    CategoryRequest createCategory(CategoryRequest categoryRequest);

    CategoryRequest createSubCategory(CategoryRequest categoryRequest);

    Page<CategoryRequest> getAllCategories(Pageable pageable);

    Page<CategoryRequest> getAllSubCategories(Pageable pageable);

    void validateIfCategoryAndSubCategotyExits (ProductCategory productCategory);

}
