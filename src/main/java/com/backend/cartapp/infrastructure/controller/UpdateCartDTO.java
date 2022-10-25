package com.backend.cartapp.infrastructure.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

import static com.fasterxml.jackson.annotation.JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = {ACCEPT_CASE_INSENSITIVE_PROPERTIES})
public class UpdateCartDTO {

    @JsonProperty("cart-id")
    public String cartId;

    @JsonProperty("product-list")
    public ArrayList<ProductDto> productDtoList;

    public UpdateCartDTO() {
    }

    public UpdateCartDTO(String cartId, ArrayList<ProductDto> productDtoList) {
        this.cartId = cartId;
        this.productDtoList = productDtoList;
    }
}
