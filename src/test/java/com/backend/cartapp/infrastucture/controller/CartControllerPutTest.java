package com.backend.cartapp.infrastucture.controller;

import com.backend.cartapp.application.CreateCartCommand;
import com.backend.cartapp.application.UpdateCart;
import com.backend.cartapp.application.UpdateCartCommand;
import com.backend.cartapp.domain.*;
import com.backend.cartapp.domain.exceptions.CartNotFoundException;
import com.backend.cartapp.domain.exceptions.InvalidDescriptionException;
import com.backend.cartapp.infrastructure.controller.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

public class CartControllerPutTest {

    private UpdateCart updateCartMock = Mockito.mock(UpdateCart.class);
    private CartControllerPut cartControllerPut = new CartControllerPut(updateCartMock);

    @Test
    void create_cart() throws CartNotFoundException {
        String  existingCartId = "38400000-8cf0-11bd-b23e-10b96e4ef00d";
        ProductDto product = new ProductDto(123456L, "product description", 25.00d);
        ArrayList<ProductDto> productList = new ArrayList<>();
        productList.add(product);
        UpdateCartDTO updateCartDTO = new UpdateCartDTO(existingCartId, productList);

        cartControllerPut.update(updateCartDTO);

        verify(updateCartMock).execute(any(UpdateCartCommand.class));

    }

    @Test
    void fail_when_create_cart_fails() throws CartNotFoundException {
        String  existingCartId = "38400000-8cf0-11bd-b23e-10b96e4ef00d";
        ProductDto product = new ProductDto(123456L, "product description", 25.00d);
        ArrayList<ProductDto> productList = new ArrayList<>();
        productList.add(product);
        UpdateCartDTO updateCartDTO = new UpdateCartDTO(existingCartId, productList);

        doThrow(new RuntimeException(
                new InvalidDescriptionException().getMessage(),
                new InvalidDescriptionException().getCause())
        ).when(updateCartMock).execute(any(UpdateCartCommand.class));

        assertThrows(RuntimeException.class, ()-> cartControllerPut.update(updateCartDTO));
    }
}
