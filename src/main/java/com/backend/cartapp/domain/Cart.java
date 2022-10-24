package com.backend.cartapp.domain;

import com.backend.cartapp.infrastructure.controller.ProductDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Cart {

    private UUID id;
    private ArrayList<Product> productDtoList;

    public Cart(ArrayList<Product> productDtoList) {
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
