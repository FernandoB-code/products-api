package com.arabot.store.productsapi.repository;

import com.arabot.store.productsapi.model.Category;
import com.arabot.store.productsapi.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubCategoryRepository extends JpaRepository <SubCategory, Long> {

    @Query(value = "SELECT * FROM subcategories WHERE name COLLATE \"C\" = ?1 LIMIT 1", nativeQuery = true)
    Optional<Category> findSingleSubCategoryByNameStrict(String name);
}
