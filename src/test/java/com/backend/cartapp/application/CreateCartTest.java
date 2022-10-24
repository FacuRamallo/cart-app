package com.backend.cartapp.application;

import com.backend.cartapp.domain.Cart;
import com.backend.cartapp.domain.contracts.CartRepository;
import com.backend.cartapp.domain.exceptions.InvalidDescriptionException;
import com.backend.cartapp.infrastructure.controller.ProductDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CreateCartTest {
    private final CartRepository cartRepository;
    private final CreateCart createCart;

    public CreateCartTest() {
        this.cartRepository = mock(CartRepository.class);
        this.createCart = new CreateCart(cartRepository);
    }


    @Test
    public void should_create_cart() {
        ProductDto product = new ProductDto(123456L, "product description", 25.00d);
        ArrayList<ProductDto> productList = new ArrayList<>();
        productList.add(product);

        CreateCartCommand createCartCommand = new CreateCartCommand(productList);

        createCart.execute(createCartCommand);

        verify(cartRepository).add(any(Cart.class));
    }

    @Test
    public void should_fail_when_products_descriptions_are_not_valid() {
        ProductDto product = new ProductDto(123456L, "invalid +,:<>*", 25.00d);
        ArrayList<ProductDto> productList = new ArrayList<>();
        productList.add(product);

        CreateCartCommand createCartCommand = new CreateCartCommand(productList);

        Exception exception = assertThrows(RuntimeException.class, () -> createCart.execute(createCartCommand));

        assertEquals(exception.getMessage(), new InvalidDescriptionException().getMessage());

    }

    @Test
    public void should_create_empty_cart(){
        ArrayList<ProductDto> productList = new ArrayList<>();
        CreateCartCommand createCartCommand = new CreateCartCommand(productList);


        createCart.execute(createCartCommand);

        verify(cartRepository).add(any(Cart.class));
    }

}
