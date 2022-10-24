package com.backend.cartapp;

import com.backend.cartapp.domain.*;
import com.backend.cartapp.domain.contracts.CartRepository;
import com.backend.cartapp.domain.exceptions.InvalidDescriptionException;
import com.backend.cartapp.infrastructure.controller.ProductDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Map;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.HttpStatus.OK;

public class GetCartFeature extends IntegrationTest {
    @Autowired
    CartRepository cartRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void get_cart_info_given_it_id() throws InvalidDescriptionException {
        Product productA = new Product(new ProductId(123456L),new Description("product description"),new Amount(25.00d));
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(productA);
        Cart cart = new Cart(productList);
        cartRepository.add(cart);

        given().get("/cart/{id}", Map.of("id", cart.getId()))
                .then()
                .status(OK)
                .contentType("application/json")
                .body("cart-id",equalTo(cart.getId().toString()))
                .body("product-list",equalTo(cart.getProductDtoList().toString()));

    }
}
