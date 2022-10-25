package com.backend.cartapp.application.deleteCart;

import com.backend.cartapp.domain.CartId;
import com.backend.cartapp.domain.contracts.CartRepository;
import com.backend.cartapp.domain.exceptions.CartNotFoundException;

public class DeleteCart {

    private final CartRepository cartRepository;

    public DeleteCart(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void execute(DeleteCartCommand command) throws CartNotFoundException {
        CartId cartId = new CartId(command.getCartId());
        boolean cartExist = cartRepository.findBy(cartId);
        if(!cartExist) throw new CartNotFoundException(cartId.id.toString());
         cartRepository.delete(cartId);
    }
}
