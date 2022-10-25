package com.backend.cartapp.application.getCart;

import com.backend.cartapp.infrastructure.controller.cartControllerGet.ProductDto;

import java.util.ArrayList;

public class CartResponseDTO {
    public String cartId;
    public ArrayList<ProductDto> productDtoList;

    public CartResponseDTO(String cartId, ArrayList<ProductDto> productDtoList) {
        this.cartId = cartId;
        this.productDtoList = productDtoList;
    }

}
