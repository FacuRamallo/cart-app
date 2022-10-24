package com.backend.cartapp.domain;

import com.backend.cartapp.infrastructure.controller.ProductDto;

import java.util.List;
import java.util.UUID;

public class Cart {

    private UUID id;
    private List<Product> productDtoList;

    public Cart(List<Product> productDtoList) {
        this.id = UUID.randomUUID();
        this.productDtoList = productDtoList;
    }

    public UUID getId() {
        return id;
    }

    public List<Product> getProductDtoList() {
        return productDtoList;
    }
}
