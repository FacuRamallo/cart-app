package com.backend.cartapp.application;

import com.backend.cartapp.application.deleteCart.DeleteCart;
import com.backend.cartapp.application.deleteCart.DeleteCartCommand;
import com.backend.cartapp.application.getCart.CartResponseDTO;
import com.backend.cartapp.application.getCart.GetCart;
import com.backend.cartapp.application.getCart.GetCartCommand;
import com.backend.cartapp.domain.Amount;
import com.backend.cartapp.domain.CartId;
import com.backend.cartapp.domain.Description;
import com.backend.cartapp.domain.Product;
import com.backend.cartapp.domain.ProductId;
import com.backend.cartapp.domain.contracts.CartRepository;
import com.backend.cartapp.domain.exceptions.CartNotFoundException;
import com.backend.cartapp.domain.exceptions.InvalidDescriptionException;
import com.backend.cartapp.infrastructure.controller.cartControllerGet.ProductDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DeleteCartTest {
    private CartRepository cartRepository;

    public DeleteCartTest() {
        this.cartRepository = mock(CartRepository.class);
    }

    @Test
    public void should_delete_cart_from_repository_by_id() throws CartNotFoundException {
        DeleteCartCommand command = new DeleteCartCommand("38400000-8cf0-11bd-b23e-10b96e4ef00d");
        DeleteCart deleteCart = new DeleteCart(cartRepository);

        when(cartRepository.findBy(any(CartId.class))).thenReturn(true);

        deleteCart.execute(command);

        verify(cartRepository).delete(any(CartId.class));
    }

    @Test
    public void should_fail_if_cartId_not_exist() {
        DeleteCartCommand command = new DeleteCartCommand("38400000-8cf0-11bd-b23e-10b96e4ef00d");
        DeleteCart deleteCart = new DeleteCart(cartRepository);

        when(cartRepository.findBy(any(CartId.class))).thenReturn(false);

        assertThrows(CartNotFoundException.class, () -> deleteCart.execute(command));
    }

}
