package com.backend.cartapp.infrastructure.controller;

import java.util.ArrayList;
import java.util.List;

public class CreateCartDTO {
    private final List<ProductDto> productDtoList;

    public CreateCartDTO(ArrayList<ProductDto> productDtoList) {
        this.productDtoList = productDtoList;
    }

    public List<ProductDto> getProductDtoList() {
        return productDtoList;
    }
}
