package com.backend.cartapp.application.createCart;

import com.backend.cartapp.domain.Amount;
import com.backend.cartapp.domain.Cart;
import com.backend.cartapp.domain.Description;
import com.backend.cartapp.domain.Product;
import com.backend.cartapp.domain.ProductId;
import com.backend.cartapp.domain.contracts.CartRepository;
import com.backend.cartapp.domain.exceptions.InvalidDescriptionException;
import com.backend.cartapp.infrastructure.controller.cartControllerGet.ProductDto;

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

        return cart.getId().id.toString();
    }

    private Cart cartFromCommand(CreateCartCommand createCartCommand) throws RuntimeException {
        ArrayList<Product> productList = createCartCommand.getProductDtoList().stream().
                map(productDto -> {
                    try {
                        return mapProductFromDTO(productDto);
                    } catch (InvalidDescriptionException e) {
                        throw new RuntimeException(e.getMessage(),e.initCause(e.getCause()));
                    }
                }).collect(Collectors.toCollection(ArrayList::new));

        return new Cart(productList);
    }

    private Product mapProductFromDTO(ProductDto productDto) throws InvalidDescriptionException {
        return new Product(
                new ProductId(productDto.id),
                new Description(productDto.description),
                new Amount(productDto.amount)
        );
    }


}
