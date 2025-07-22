package com.arabot.store.productsapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
public class ProductAttribute {
    @NotBlank
    private String attributeName;
    @NotBlank
    private String attributeDescription;
}
