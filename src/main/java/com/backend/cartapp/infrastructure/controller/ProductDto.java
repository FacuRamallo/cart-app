package com.backend.cartapp.infrastructure.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.fasterxml.jackson.annotation.JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = {ACCEPT_CASE_INSENSITIVE_PROPERTIES})
public class ProductDto {
    public Long id;
    public String description;
    public Double amount;

    public ProductDto() {
    }

    public ProductDto(Long id, String description, Double amount) {
        this.id = id;
        this.description = description;
        this.amount = amount;
    }
}
