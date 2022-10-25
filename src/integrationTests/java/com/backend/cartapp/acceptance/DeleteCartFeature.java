package com.backend.cartapp.acceptance;

import com.backend.cartapp.IntegrationTest;
import com.backend.cartapp.application.getCart.CartResponseDTO;
import com.backend.cartapp.domain.Amount;
import com.backend.cartapp.domain.Cart;
import com.backend.cartapp.domain.Description;
import com.backend.cartapp.domain.Product;
import com.backend.cartapp.domain.ProductId;
import com.backend.cartapp.domain.contracts.CartRepository;
import com.backend.cartapp.domain.exceptions.InvalidDescriptionException;
import com.backend.cartapp.infrastructure.controller.cartControllerGet.ProductDto;
import com.backend.cartapp.infrastructure.controller.cartControllerPost.CartDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Map;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.http.HttpStatus.OK;

class DeleteCartFeature extends IntegrationTest {

    @Autowired
    CartRepository cartRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void  should_delete_cart() throws InvalidDescriptionException {
        Product productA = new Product(new ProductId(123456L),new Description("product description"),new Amount(25.00d));
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(productA);
        Cart cart = new Cart(productList);

        cartRepository.add(cart);

        given().delete("/cart/{id}", Map.of("id", cart.getId().id.toString()))
                .then()
                .status(OK);


        assertFalse(cartRepository.findBy(cart.getId()));
    }

}
