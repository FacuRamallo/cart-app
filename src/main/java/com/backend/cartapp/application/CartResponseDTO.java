package com.backend.cartapp.application;

import com.backend.cartapp.infrastructure.controller.ProductDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class CartResponseDTO {
    public String cartId;
    public ArrayList<ProductDto> productDtoList;

    public CartResponseDTO(String cartId, ArrayList<ProductDto> productDtoList) {
        this.cartId = cartId;
        this.productDtoList = productDtoList;
    }

}
