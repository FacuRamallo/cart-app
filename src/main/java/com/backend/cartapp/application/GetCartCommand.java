package com.backend.cartapp.application;

public class GetCartCommand {
    private final String cartId;

    public GetCartCommand(String cartId) {
        this.cartId = cartId;
    }

    public String getCartId() {
        return cartId;
    }
}
