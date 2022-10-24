package com.backend.cartapp.infrastucture.controller;

import com.backend.cartapp.application.GetCart;
import com.backend.cartapp.application.GetCartCommand;
import com.backend.cartapp.domain.exceptions.CartNotFoundException;
import com.backend.cartapp.infrastructure.controller.CartControllerGet;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class CartControllerGetTest {
    private GetCart getCart = mock(GetCart.class);

    private CartControllerGet controllerGet = new CartControllerGet(getCart);


    @Test
    public void should_fail_when_getCart_fails() throws CartNotFoundException {
        String cartId = "38400000-8cf0-11bd-b23e-10b96e4ef00d";


        when(getCart.execute(any(GetCartCommand.class))).thenThrow(new CartNotFoundException(cartId));

        assertThrows(CartNotFoundException.class, () -> controllerGet.get(cartId));
    }
}
