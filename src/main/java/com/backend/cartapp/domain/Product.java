package com.backend.cartapp.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import static com.fasterxml.jackson.annotation.JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = {ACCEPT_CASE_INSENSITIVE_PROPERTIES})
public class Product {
    private ProductId id;
    private Description description;
    private Amount amount;

    public Product(ProductId id, Description description, Amount amount) {
        this.id = id;
        this.description = description;
        this.amount = amount;
    }

    public ProductId getId() {
        return id;
    }

    public Description getDescription() {
        return description;
    }

    public Amount getAmount() {
        return amount;
    }
}
