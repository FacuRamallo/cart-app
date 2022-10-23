package com.backend.cartapp.infrastructure.controller;

public class ProductDto {
    private final Long id;
    private final String description;
    private final Integer amount;

    public ProductDto(Long id, String description, Integer amount) {
        this.id = id;
        this.description = description;
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public Integer getAmount() {
        return amount;
    }
}
