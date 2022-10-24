package com.backend.cartapp.domain.contracts;

import com.backend.cartapp.domain.Cart;

import java.util.UUID;

public interface CartRepository {

    void add(Cart cart);

    boolean findBy(UUID cartId);
}
