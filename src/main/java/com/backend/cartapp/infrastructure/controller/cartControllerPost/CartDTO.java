package com.backend.cartapp.infrastructure.controller.cartControllerPost;

import com.backend.cartapp.infrastructure.controller.cartControllerGet.ProductDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

import static com.fasterxml.jackson.annotation.JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = {ACCEPT_CASE_INSENSITIVE_PROPERTIES})
public class CartDTO {
    @JsonProperty("product-list")
    public ArrayList<ProductDto> productDtoList;

    public CartDTO() {
    }

    public CartDTO(ArrayList<ProductDto> productDtoList) {
        this.productDtoList = productDtoList;
    }
}
