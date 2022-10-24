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

public class GetCartTest {
    private CartRepository cartRepository;

    public GetCartTest() {
        this.cartRepository = mock(CartRepository.class);
    }

    @Test
    public void should_get_cart_from_repository_by_id() throws InvalidDescriptionException, CartNotFoundException {
        GetCartCommand command = new GetCartCommand("38400000-8cf0-11bd-b23e-10b96e4ef00d");
        GetCart getCart = new GetCart(cartRepository);

        Product product = new Product(
                new ProductId(123456L),
                new Description("product description"),
                new Amount(25.00d)
        );
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);

        ArrayList<ProductDto> expectedProductDTOs = new ArrayList<>();
        expectedProductDTOs.add(mapToProductDto(product));

        CartResponseDTO expectedResponse = new CartResponseDTO(command.getCartId(), expectedProductDTOs);

        when(cartRepository.findBy(any(CartId.class))).thenReturn(true);
        when(cartRepository.getBy(any(CartId.class))).thenReturn(productList);

        CartResponseDTO response = getCart.execute(command);

        verify(cartRepository).getBy(any(CartId.class));

        assertEquals(expectedResponse.cartId, response.cartId);
        assertEquals(expectedResponse.productDtoList.get(0).id, response.productDtoList.get(0).id);
        assertEquals(expectedResponse.productDtoList.get(0).description, response.productDtoList.get(0).description);
        assertEquals(expectedResponse.productDtoList.get(0).amount, response.productDtoList.get(0).amount);
    }

    @Test
    public void should_fail_if_cartId_not_exist() {
        GetCartCommand command = new GetCartCommand("38400000-8cf0-11bd-b23e-10b96e4ef00d");
        GetCart getCart = new GetCart(cartRepository);

        when(cartRepository.findBy(any(CartId.class))).thenReturn(false);

        assertThrows(CartNotFoundException.class, () -> getCart.execute(command));
    }

    private ProductDto mapToProductDto(Product product) {
        return new ProductDto(
                product.getId().getValue(),
                product.getDescription().getText(),
                product.getAmount().getValue()
        );
    }
}
