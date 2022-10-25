package com.backend.cartapp.acceptance;

import com.backend.cartapp.IntegrationTest;
import com.backend.cartapp.application.createCart.CreateCartCommand;
import com.backend.cartapp.domain.exceptions.InvalidDescriptionException;
import com.backend.cartapp.infrastructure.controller.cartControllerGet.ProductDto;
import com.backend.cartapp.infrastructure.controller.cartControllerPost.CartDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.OK;

public class DeleteCartTaskFeature extends IntegrationTest {

    @Test
    public void should_delete_cart_after_given_TTL() throws InterruptedException, JsonProcessingException {
        ProductDto productA = new ProductDto(123456L, "product description", 25.00d);
        ArrayList<ProductDto> productList = new ArrayList<>();
        productList.add(productA);
        CartDTO cartDTO = new CartDTO(productList);

        String jsonCreateCartDTO = objectMapper.writeValueAsString(cartDTO);

        given()
            .contentType(APPLICATION_JSON)
            .body(jsonCreateCartDTO)
            .post("/cart")
            .then()
            .status(OK);

        Thread.sleep(4000L);

        assertEquals(0, cartRepository.getCartsQuantity());
    }

    @Test
    public void should_not_fail_if_cart_does_not_exist() throws InterruptedException, InvalidDescriptionException {
        ProductDto product = new ProductDto(123456L, "product description", 25.00d);
        ArrayList<ProductDto> productList = new ArrayList<>();
        productList.add(product);

        CreateCartCommand createCartCommand = new CreateCartCommand(productList);

        String cartId = createCart.execute(createCartCommand);

        assertEquals(1, cartRepository.getCartsQuantity());

        given().delete("/cart/{id}", Map.of("id", cartId))
                .then()
                .status(OK);

        assertEquals(0, cartRepository.getCartsQuantity());

        Thread.sleep(4000L);
    }

}
