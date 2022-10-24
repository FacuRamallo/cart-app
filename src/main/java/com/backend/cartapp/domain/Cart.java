package com.backend.cartapp.domain;

import com.backend.cartapp.infrastructure.controller.ProductDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Cart {

    private CartId id;
    private ArrayList<Product> productDtoList;

    public Cart(ArrayList<Product> productDtoList) {
        this.id = new CartId();
        this.productDtoList = productDtoList;
    }

    public CartId getId() {
        return id;
    }

    public ArrayList<Product> getProductDtoList() {
        return productDtoList;
    }
}
