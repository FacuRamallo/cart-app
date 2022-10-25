package com.backend.cartapp.infrastucture.controller;

import com.backend.cartapp.application.deleteCart.DeleteCart;
import com.backend.cartapp.application.deleteCart.DeleteCartCommand;
import com.backend.cartapp.application.getCart.GetCart;
import com.backend.cartapp.application.getCart.GetCartCommand;
import com.backend.cartapp.application.updateCart.UpdateCartCommand;
import com.backend.cartapp.domain.CartId;
import com.backend.cartapp.domain.exceptions.CartNotFoundException;
import com.backend.cartapp.infrastructure.controller.cartControllerDelete.CartControllerDelete;
import com.backend.cartapp.infrastructure.controller.cartControllerGet.CartControllerGet;
import com.backend.cartapp.infrastructure.controller.cartControllerPut.UpdateCartDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CartControllerDeleteTest {
    private DeleteCart deleteCart = mock(DeleteCart.class);

    private CartControllerDelete controllerDelete = new CartControllerDelete(deleteCart);

    @Test
    void should_delete_cart() throws CartNotFoundException {
        String cartId = "38400000-8cf0-11bd-b23e-10b96e4ef00d";

        controllerDelete.delete(cartId);

        verify(deleteCart).execute(any(DeleteCartCommand.class));
    }

    @Test
    public void should_fail_when_getCart_fails() throws CartNotFoundException {
        String cartId = "38400000-8cf0-11bd-b23e-10b96e4ef00d";

        doThrow(new CartNotFoundException(cartId)).when(deleteCart).execute(any());

        assertThrows(CartNotFoundException.class, ()-> controllerDelete.delete(cartId));

    }
}
