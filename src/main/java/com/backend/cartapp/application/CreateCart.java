package com.backend.cartapp.application;

import com.backend.cartapp.domain.*;
import com.backend.cartapp.domain.contracts.CartRepository;
import com.backend.cartapp.domain.exceptions.InvalidDescriptionException;
import com.backend.cartapp.infrastructure.controller.ProductDto;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CreateCart {

    private final CartRepository cartRepository;
    public CreateCart(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public String execute(CreateCartCommand command) throws RuntimeException {

        Cart cart = cartFromCommand(command);

        cartRepository.add(cart);

        return cart.getId().toString();
    }

    private Cart cartFromCommand(CreateCartCommand createCartCommand) throws RuntimeException {
        ArrayList<Product> productList = createCartCommand.getProductDtoList().stream().
                map(productDto -> {
                    try {
                        return getProductFromDTO(productDto);
                    } catch (InvalidDescriptionException e) {
                        throw new RuntimeException(e.getMessage(),e.initCause(e.getCause()));
                    }
                }).collect(Collectors.toCollection(ArrayList::new));

        return new Cart(productList);
    }

    private Product getProductFromDTO(ProductDto productDto) throws InvalidDescriptionException {
        return new Product(
                new ProductId(productDto.id),
                new Description(productDto.description),
                new Amount(productDto.amount)
        );
    }


}
