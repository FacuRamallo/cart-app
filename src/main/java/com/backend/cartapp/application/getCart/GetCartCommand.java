package com.backend.cartapp.application.getCart;

public class GetCartCommand {
    private final String cartId;

    public GetCartCommand(String cartId) {
        this.cartId = cartId;
    }

    public String getCartId() {
        return cartId;
    }
}
