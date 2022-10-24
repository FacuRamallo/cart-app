package com.backend.cartapp.domain.exceptions;

public class CartNotFoundException extends Throwable {

    public CartNotFoundException(String id) {
        super("Cart with id ="+id+" not found.");
    }
}
