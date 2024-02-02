package com.arabot.store.productsapi.model;

import com.arabot.store.productsapi.dto.ProductAttribute;
import com.arabot.store.productsapi.dto.ProductCategory;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;
import java.util.UUID;

@Document
@Data
public class Product {

    @Id
    private UUID id;
    private String name;
    private double price;
    private int stock;
    private String brand;
    private ProductCategory productCategory;
    private Set<ProductAttribute> productAttributes;

}
