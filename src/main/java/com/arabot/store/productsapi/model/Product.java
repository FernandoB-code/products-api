package com.arabot.store.productsapi.model;

import com.arabot.store.productsapi.dto.ProductCategory;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private BigDecimal price;

    private String description;

    @Embedded
    private ProductCategory productCategory;

    private boolean isErased = false;
}
