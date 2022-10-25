package com.backend.cartapp.application.deleteCart;

public class DeleteCartCommand {
    private final String cartId;

    public DeleteCartCommand(String cartId) {
        this.cartId = cartId;
    }

    public String getCartId() {
        return cartId;
    }
}
