package com.backend.cartapp.application.getCart;

import com.backend.cartapp.domain.CartId;
import com.backend.cartapp.domain.Product;
import com.backend.cartapp.domain.contracts.CartRepository;
import com.backend.cartapp.domain.exceptions.CartNotFoundException;
import com.backend.cartapp.infrastructure.controller.cartControllerGet.ProductDto;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class GetCart {

    private final CartRepository cartRepository;

    public GetCart(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public CartResponseDTO execute(GetCartCommand command) throws CartNotFoundException {
        CartId cartId = new CartId(command.getCartId());

        if(!cartRepository.findBy(cartId)) throw new CartNotFoundException(cartId.id.toString());

        ArrayList<Product> products = cartRepository.getBy(cartId);

        return mapToCartDto(cartId, products);
    }

    private ProductDto mapToProductDto(Product product) {
        return new ProductDto(
            product.getId().getValue(),
            product.getDescription().getText(),
            product.getAmount().getValue()
        );
    }

    private CartResponseDTO mapToCartDto(CartId id, ArrayList<Product> productsList) {
        ArrayList<ProductDto> productDtoList =
            productsList.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toCollection(ArrayList::new));

        return new CartResponseDTO(id.id.toString(),productDtoList);
    }
}
