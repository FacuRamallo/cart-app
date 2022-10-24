package com.backend.cartapp.infrastructure.repository;

import com.backend.cartapp.domain.Cart;
import com.backend.cartapp.domain.Product;
import com.backend.cartapp.domain.contracts.CartRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class InMemoryCartRepository implements CartRepository {

    private HashMap storage;

    public InMemoryCartRepository() {
        this.storage = new HashMap<UUID, ArrayList<Product>>();
    }

    @Override
    public void add(Cart cart) {
        storage.put(cart.getId(),cart.getProductDtoList());
    }

    @Override
    public boolean findBy(UUID cartId) {
        return storage.containsKey(cartId);
    }

}
