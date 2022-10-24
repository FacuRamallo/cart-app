package com.backend.cartapp;

import com.backend.cartapp.domain.contracts.CartRepository;
import com.backend.cartapp.infrastructure.controller.CreateCartDTO;
import com.backend.cartapp.infrastructure.controller.ProductDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.springframework.http.HttpStatus.OK;

class CreateCartFeature extends IntegrationTest {

    @Autowired
    CartRepository cartRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void  should_create_cart() throws JsonProcessingException {
        ProductDto productA = new ProductDto(123456L, "product description", 25.00d);
        ArrayList<ProductDto> productList = new ArrayList<>();
        productList.add(productA);
        CreateCartDTO createCartDTO = new CreateCartDTO(productList);

        String jsonCreateCartDTO = objectMapper.writeValueAsString(createCartDTO);

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
        CreateCartDTO createCartDTO = new CreateCartDTO(productList);

        String jsonCreateCartDTO = objectMapper.writeValueAsString(createCartDTO);

        RestAssuredMockMvc.given()
                .contentType(APPLICATION_JSON)
                .body(jsonCreateCartDTO)
                .post("/cart")
                .then()
                .status(OK);

    }
}
