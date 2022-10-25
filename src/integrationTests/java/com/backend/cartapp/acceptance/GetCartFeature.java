package com.backend.cartapp.acceptance;

import com.backend.cartapp.IntegrationTest;
import com.backend.cartapp.application.getCart.CartResponseDTO;
import com.backend.cartapp.domain.*;
import com.backend.cartapp.domain.contracts.CartRepository;
import com.backend.cartapp.domain.exceptions.InvalidDescriptionException;
import com.backend.cartapp.infrastructure.controller.cartControllerGet.ProductDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.*;
import static org.springframework.http.HttpStatus.OK;

public class GetCartFeature extends IntegrationTest {
    @Autowired
    CartRepository cartRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void get_cart_info_given_it_id() throws InvalidDescriptionException, JsonProcessingException {
        Product productA = new Product(new ProductId(123456L),new Description("product description"),new Amount(25.00d));
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(productA);
        Cart cart = new Cart(productList);

        cartRepository.add(cart);

        CartResponseDTO expectedCartDto = mapToCartDto(cart.getId(),cart.getProductList());

        given().get("/cart/{id}", Map.of("id", cart.getId().id.toString()))
                .then()
                .status(OK)
                .contentType("application/json")
                .body("cart-ID",equalTo(cart.getId().id.toString()))
                .body(containsString(objectMapper.writeValueAsString(expectedCartDto.productDtoList)));

    }

    private ProductDto mapToProductDto(Product product) {
        return new ProductDto(
                product.getId().getValue(),
                product.getDescription().getText(),
                product.getAmount().getValue()
        );
    }

    private CartResponseDTO mapToCartDto(CartId id, ArrayList<Product> productsList) {
        ArrayList<ProductDto> productDtoList =
                productsList.stream()
                        .map(this::mapToProductDto)
                        .collect(Collectors.toCollection(ArrayList::new));

        return new CartResponseDTO(id.id.toString(),productDtoList);
    }
    private String mapToString(CartResponseDTO cart) {
        try {
            return objectMapper.writeValueAsString(cart.productDtoList);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
