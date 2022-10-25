package com.backend.cartapp.infrastructure.repository;

import com.backend.cartapp.domain.Cart;
import com.backend.cartapp.domain.CartId;
import com.backend.cartapp.domain.Product;
import com.backend.cartapp.domain.contracts.CartRepository;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryCartRepository implements CartRepository {

    private ConcurrentHashMap<UUID,ArrayList<Product>> storage;

    public InMemoryCartRepository() {
        this.storage = new ConcurrentHashMap<>();
    }

    @Override
    public void add(Cart cart) {
        storage.put(cart.getId().id,cart.getProductList());
    }

    @Override
    public boolean findBy(CartId cartId) {
        return storage.containsKey(cartId.id);
    }

    @Override
    public ArrayList<Product> getBy(CartId cartId) {
        return storage.get(cartId.id);
    }

    @Override
    public void addProductsTo(Cart cart) {
        ArrayList<Product> storageProductList = storage.get(cart.getId().id);
        storageProductList.addAll(cart.getProductList());
        storage.put(cart.getId().id, storageProductList);
    }

    @Override
    public void delete(CartId cartId) {
        storage.remove(cartId.id);
    }

    public int getCartsQuantity(){
        return this.storage.size();
    }

    public void truncate() {
        this.storage.clear();
    }

}
