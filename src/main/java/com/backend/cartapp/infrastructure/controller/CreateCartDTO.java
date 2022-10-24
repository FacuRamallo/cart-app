package com.backend.cartapp.infrastructure.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = {ACCEPT_CASE_INSENSITIVE_PROPERTIES})
public class CreateCartDTO {
    @JsonProperty("product-list")
    public ArrayList<ProductDto> productDtoList;

    public CreateCartDTO() {
    }

    public CreateCartDTO(ArrayList<ProductDto> productDtoList) {
        this.productDtoList = productDtoList;
    }
}
