package com.arabot.store.productsapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@MappedSuperclass
public class BaseCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "name")
    public String name;

}
