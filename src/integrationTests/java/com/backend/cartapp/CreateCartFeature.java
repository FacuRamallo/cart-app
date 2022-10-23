package com.backend.cartapp;

import com.backend.cartapp.infrastructure.controller.CreateCartDTO;
import com.backend.cartapp.infrastructure.controller.ProductDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.springframework.http.HttpStatus.CREATED;

class CreateCartFeature extends IntegrationTest {


    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void  should_create_cart() throws JsonProcessingException {
        ProductDto productA = new ProductDto(123456L, "product description", 25);
        ArrayList<ProductDto> productList = new ArrayList<>();
        productList.add(productA);
        CreateCartDTO createCartDTO = new CreateCartDTO(productList);

        String jsonCreateCartDTO = objectMapper.writeValueAsString(createCartDTO);

        RestAssuredMockMvc.given()
            .contentType(APPLICATION_JSON)
            .body(jsonCreateCartDTO)
            .post("/cart")
            .then()
            .status(CREATED);
    }
}
