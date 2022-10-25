package com.backend.cartapp.domain;

import java.util.ArrayList;

public class Cart {

    private CartId id;
    private ArrayList<Product> productList;

    public Cart(ArrayList<Product> productDtoList) {
        this.id = new CartId();
        this.productList = productDtoList;
    }

    public Cart(CartId id, ArrayList<Product> productDtoList) {
        this.id = id;
        this.productList = productDtoList;
    }

    public CartId getId() {
        return id;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }
}
