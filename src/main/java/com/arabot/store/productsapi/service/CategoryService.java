package com.arabot.store.productsapi.service;

import com.arabot.store.productsapi.dto.ProductCategory;
import com.arabot.store.productsapi.dto.CategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO createSubCategory(CategoryDTO categoryDTO);

    Page<CategoryDTO> getAllCategories(Pageable pageable);

    Page<CategoryDTO> getAllSubCategories(Pageable pageable);

    void validateIfCategoryAndSubCategoryExits(ProductCategory productCategory);

}
