package com.backend.cartapp;

import com.backend.cartapp.domain.*;
import com.backend.cartapp.domain.exceptions.InvalidDescriptionException;
import com.backend.cartapp.infrastructure.controller.CartDTO;
import com.backend.cartapp.infrastructure.controller.ProductDto;
import com.backend.cartapp.infrastructure.controller.UpdateCartDTO;
import com.backend.cartapp.infrastructure.repository.InMemoryCartRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.HttpStatus.OK;

public class UpdateCartFeature extends IntegrationTest {

    @Autowired
    private InMemoryCartRepository cartRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void should_add_products_to_an_existing_cart() throws JsonProcessingException, InvalidDescriptionException {
        Product productA = new Product(new ProductId(654321L),new Description("product description"),new Amount(50.00d));
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(productA);
        Cart cart = new Cart(productList);

        cartRepository.add(cart);

        String cartId = cart.getId().id.toString();
        ProductDto productToAdd = new ProductDto(123456L, "product description", 25.00d);
        ArrayList<ProductDto> productListDto = new ArrayList<>();
        productListDto.add(productToAdd);
        UpdateCartDTO updateCartDTO = new UpdateCartDTO(cartId, productListDto);

        String jsonCreateCartDTO = objectMapper.writeValueAsString(updateCartDTO);

        given()
            .contentType(APPLICATION_JSON)
            .body(jsonCreateCartDTO)
            .put("/cart")
            .then()
            .status(OK)
            .body("result",equalToIgnoringCase("cart updated correctly"));

        assertEquals(2, cartRepository.getBy(cart.getId()).size());
    }

}
