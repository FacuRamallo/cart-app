package com.backend.cartapp.application.deleteCart;

import com.backend.cartapp.application.getCart.CartResponseDTO;
import com.backend.cartapp.application.getCart.GetCartCommand;
import com.backend.cartapp.domain.CartId;
import com.backend.cartapp.domain.Product;
import com.backend.cartapp.domain.contracts.CartRepository;
import com.backend.cartapp.domain.exceptions.CartNotFoundException;
import com.backend.cartapp.infrastructure.controller.cartControllerGet.ProductDto;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class DeleteCart {

    private final CartRepository cartRepository;

    public DeleteCart(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void execute() {

    }
}
