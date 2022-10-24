package com.backend.cartapp.domain;

public class ProductId {
    private Long value;

    public ProductId(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "\""+this.value.toString()+"\"";
    }
}
