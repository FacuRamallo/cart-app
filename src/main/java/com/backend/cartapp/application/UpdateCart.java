package com.backend.cartapp.application;

import com.backend.cartapp.domain.*;
import com.backend.cartapp.domain.contracts.CartRepository;
import com.backend.cartapp.domain.exceptions.CartNotFoundException;
import com.backend.cartapp.domain.exceptions.InvalidDescriptionException;
import com.backend.cartapp.infrastructure.controller.ProductDto;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class UpdateCart {

    private final CartRepository cartRepository;

    public UpdateCart(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void execute(UpdateCartCommand command) throws RuntimeException, CartNotFoundException {

        CartId cartId = new CartId(command.getCartId());
        ArrayList<Product> productsToAdd = productsFromCommand(command);

        Cart cartToUpdate = new Cart(cartId, productsToAdd);
        Boolean cartExist = cartRepository.findBy(cartToUpdate.getId());
        if(!cartExist) throw new CartNotFoundException(cartId.id.toString());

        cartRepository.addProductsTo(cartToUpdate);

    }

    private ArrayList<Product> productsFromCommand(UpdateCartCommand updateCartCommand) throws RuntimeException {
        return updateCartCommand.getProductDtoList().stream().
                map(productDto -> {
                    try {
                        return getProductFromDTO(productDto);
                    } catch (InvalidDescriptionException e) {
                        throw new RuntimeException(e.getMessage(),e.initCause(e.getCause()));
                    }
                }).collect(Collectors.toCollection(ArrayList::new));
    }

    private Product getProductFromDTO(ProductDto productDto) throws InvalidDescriptionException {
        return new Product(
                new ProductId(productDto.id),
                new Description(productDto.description),
                new Amount(productDto.amount)
        );
    }


}
