package com.arabot.store.productsapi.repository;

import com.arabot.store.productsapi.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubCategoryRepository extends JpaRepository <SubCategory, Long> {

    Optional<SubCategory> findByName(final String name);
}
