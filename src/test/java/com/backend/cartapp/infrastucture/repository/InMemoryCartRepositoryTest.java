package com.backend.cartapp.infrastucture.repository;

import com.backend.cartapp.domain.Cart;
import com.backend.cartapp.domain.repository.InMemoryCartRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InMemoryCartRepositoryTest {


    @Test
    public void should_ad_new_cart() {
        InMemoryCartRepository repository = new InMemoryCartRepository();

        Cart cart = new Cart(new ArrayList<>());

        repository.add(cart);

        assertTrue(repository.findBy(cart.getId()));
    }
}