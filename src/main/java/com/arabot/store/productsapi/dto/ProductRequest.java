package com.arabot.store.productsapi.dto;

import com.arabot.store.productsapi.dto.ProductCategory;
import com.arabot.store.productsapi.dto.ProductAttribute;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Validated
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    private UUID id;
    @NotBlank
    private String name;
    @NotNull
    private Double price;
    @NotNull
    private Integer stock;
    @NotBlank
    private String brand;
    @NotNull
    private ProductCategory productCategory;
    @NotNull
    private Set<ProductAttribute> productAttributes;

}
