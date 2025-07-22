package com.arabot.store.productsapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

 @NotBlank
 public String name;

}
