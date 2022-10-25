package com.backend.cartapp.application.updateCart;

import com.backend.cartapp.infrastructure.controller.cartControllerGet.ProductDto;

import java.util.List;

public class UpdateCartCommand {

    private final String cartId;
    private final List<ProductDto> productDtoList;

    public UpdateCartCommand(String cartId, List<ProductDto> productDtoList) {
        this.cartId = cartId;
        this.productDtoList = productDtoList;
    }

    public List<ProductDto> getProductDtoList() {
        return productDtoList;
    }

    public String getCartId() {
        return cartId;
    }
}
