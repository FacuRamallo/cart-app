package com.backend.cartapp.domain;

public class Amount {
    private Double value;

    public Amount(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }
    @Override
    public String toString() {
        return "\""+this.value.toString()+"\"";
    }
}
