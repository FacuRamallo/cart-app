package com.backend.cartapp.acceptance;

import com.backend.cartapp.IntegrationTest;
import com.backend.cartapp.infrastructure.controller.cartControllerGet.ProductDto;
import com.backend.cartapp.infrastructure.controller.cartControllerPost.CartDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.springframework.http.HttpStatus.OK;

class CreateCartFeature extends IntegrationTest {

    @Test
    void  should_create_cart() throws JsonProcessingException {
        ProductDto productA = new ProductDto(123456L, "product description", 25.00d);
        ArrayList<ProductDto> productList = new ArrayList<>();
        productList.add(productA);
        CartDTO cartDTO = new CartDTO(productList);

        String jsonCreateCartDTO = objectMapper.writeValueAsString(cartDTO);

        RestAssuredMockMvc.given()
            .contentType(APPLICATION_JSON)
            .body(jsonCreateCartDTO)
            .post("/cart")
            .then()
            .status(OK);
    }

    @Test
    void  should_create_empty_cart() throws JsonProcessingException {
        ArrayList<ProductDto> productList = new ArrayList<>();
        CartDTO cartDTO = new CartDTO(productList);

        String jsonCreateCartDTO = objectMapper.writeValueAsString(cartDTO);

        RestAssuredMockMvc.given()
                .contentType(APPLICATION_JSON)
                .body(jsonCreateCartDTO)
                .post("/cart")
                .then()
                .status(OK);
    }
}
