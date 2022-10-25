package com.backend.cartapp.acceptance;

import com.backend.cartapp.IntegrationTest;
import com.backend.cartapp.domain.Amount;
import com.backend.cartapp.domain.Cart;
import com.backend.cartapp.domain.Description;
import com.backend.cartapp.domain.Product;
import com.backend.cartapp.domain.ProductId;
import com.backend.cartapp.domain.exceptions.InvalidDescriptionException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.http.HttpStatus.OK;

class DeleteCartFeature extends IntegrationTest {

    @Test
    void  should_delete_cart() throws InvalidDescriptionException {
        Product productA = new Product(new ProductId(123456L),new Description("product description"),new Amount(25.00d));
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(productA);
        Cart cart = new Cart(productList);

        cartRepository.add(cart);

        given().delete("/cart/{id}", Map.of("id", cart.getId().id.toString()))
                .then()
                .status(OK)
                .body("message",equalToIgnoringCase("Cart id="+cart.getId().id+" deleted"));


        assertFalse(cartRepository.findBy(cart.getId()));
    }

}
