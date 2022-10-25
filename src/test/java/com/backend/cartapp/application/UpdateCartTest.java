package com.backend.cartapp.application;

import com.backend.cartapp.domain.*;
import com.backend.cartapp.domain.contracts.CartRepository;
import com.backend.cartapp.domain.exceptions.CartNotFoundException;
import com.backend.cartapp.domain.exceptions.InvalidDescriptionException;
import com.backend.cartapp.infrastructure.controller.ProductDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UpdateCartTest {
    private final CartRepository cartRepository;
    private final UpdateCart updateCart;

    public UpdateCartTest() {
        this.cartRepository = mock(CartRepository.class);
        this.updateCart = new UpdateCart(cartRepository);
    }


    @Test
    public void should_update_cart() throws CartNotFoundException {
        String  existingCartId = "38400000-8cf0-11bd-b23e-10b96e4ef00d";
        ProductDto product = new ProductDto(123456L, "new product description", 25.00d);
        ArrayList<ProductDto> productList = new ArrayList<>();
        productList.add(product);

        UpdateCartCommand createCartCommand = new UpdateCartCommand(existingCartId, productList);

        when(cartRepository.findBy(any(CartId.class))).thenReturn(true);

        updateCart.execute(createCartCommand);

        verify(cartRepository).addProductsTo(any(Cart.class));
    }

    @Test
    public void should_fail_when_products_descriptions_are_not_valid() {
        String  existingCartId = "38400000-8cf0-11bd-b23e-10b96e4ef00d";
        ProductDto product = new ProductDto(123456L, "invalid +,:<>*", 25.00d);
        ArrayList<ProductDto> productList = new ArrayList<>();
        productList.add(product);

        UpdateCartCommand updateCartCommand = new UpdateCartCommand(existingCartId, productList);

        Exception exception = assertThrows(RuntimeException.class, () -> updateCart.execute(updateCartCommand));

        String expectedExceptionMessage = new InvalidDescriptionException().getMessage();

        assertEquals(exception.getMessage(), expectedExceptionMessage);

    }

    @Test
    public void should_fail_if_cartId_not_exist() {
        String  existingCartId = "38400000-8cf0-11bd-b23e-10b96e4ef00d";
        ProductDto product = new ProductDto(123456L, "new product description", 25.00d);
        ArrayList<ProductDto> productList = new ArrayList<>();
        productList.add(product);

        UpdateCartCommand command = new UpdateCartCommand(existingCartId, productList);

        when(cartRepository.findBy(any(CartId.class))).thenReturn(false);

        assertThrows(CartNotFoundException.class, () -> updateCart.execute(command));
    }

}
