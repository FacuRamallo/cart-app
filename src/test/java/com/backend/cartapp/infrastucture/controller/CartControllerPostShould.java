package com.backend.cartapp.infrastucture.controller;

import com.backend.cartapp.application.CreateCart;
import com.backend.cartapp.application.CreateCartCommand;
import com.backend.cartapp.domain.exceptions.InvalidDescriptionException;
import com.backend.cartapp.infrastructure.controller.CartControllerPost;
import com.backend.cartapp.infrastructure.controller.CreateCartDTO;
import com.backend.cartapp.infrastructure.controller.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CartControllerPostShould {

    private CreateCart createCartMock = Mockito.mock(CreateCart.class);
    private CartControllerPost cartControllerPost = new CartControllerPost(createCartMock);

    @Test
    void create_cart() {
        ProductDto product = new ProductDto(123456L, "product description", 25.00d);
        ArrayList<ProductDto> productList = new ArrayList<>();
        productList.add(product);
        CreateCartDTO createCartDTO = new CreateCartDTO(productList);

        cartControllerPost.create(createCartDTO);

        verify(createCartMock).execute(any(CreateCartCommand.class));

    }

    @Test
    void fail_when_create_cart_fails() {
        ProductDto product = new ProductDto(123456L, "invalid +,:<>*", 25.00d);
        ArrayList<ProductDto> productList = new ArrayList<>();
        productList.add(product);
        CreateCartDTO createCartDTO = new CreateCartDTO(productList);

        doThrow(new RuntimeException(
                new InvalidDescriptionException().getMessage(),
                new InvalidDescriptionException().getCause())
        ).when(createCartMock).execute(any());

        assertThrows(RuntimeException.class, ()-> cartControllerPost.create(createCartDTO));
    }
}
