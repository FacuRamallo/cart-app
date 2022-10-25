package com.backend.cartapp.application.createCart;

import com.backend.cartapp.infrastructure.controller.cartControllerGet.ProductDto;

import java.util.List;

public class CreateCartCommand {

    private final List<ProductDto> productDtoList;

    public CreateCartCommand(List<ProductDto> productDtoList) {
        this.productDtoList = productDtoList;
    }

    public List<ProductDto> getProductDtoList() {
        return productDtoList;
    }
}
