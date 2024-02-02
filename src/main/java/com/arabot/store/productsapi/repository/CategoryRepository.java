package com.arabot.store.productsapi.repository;

import com.arabot.store.productsapi.model.BaseCategory;
import com.arabot.store.productsapi.model.Category;
import com.arabot.store.productsapi.model.Product;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository <Category, Long> {

    Optional<Category> findByName(final String name);

    @Query(value = "SELECT * FROM categories WHERE BINARY name = ?1 LIMIT 1", nativeQuery = true)
    Optional<Category> findSingleCategoryByNameStrict(String name);

    @Query(value = "SELECT * FROM subcategories WHERE BINARY name = ?1 LIMIT 1", nativeQuery = true)
    Optional<Category> findSingleSubCategoryByNameStrict(String name);

    Page<Category> findAll(Pageable pageable);


}
