package com.arabot.store.productsapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.Mapping;

@Entity
@Table(name = "categories")
@Data
public class Category extends BaseCategory {

}