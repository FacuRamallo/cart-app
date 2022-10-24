package com.backend.cartapp.domain;

public class Product {
    private ProductId id;
    private Description description;
    private Amount amount;

    public Product(ProductId id, Description description, Amount amount) {
        this.id = id;
        this.description = description;
        this.amount = amount;
    }
}
