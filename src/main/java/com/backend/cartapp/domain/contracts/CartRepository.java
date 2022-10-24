package com.backend.cartapp.domain.contracts;

import com.backend.cartapp.domain.Cart;
import com.backend.cartapp.domain.CartId;
import com.backend.cartapp.domain.Product;

import java.util.ArrayList;

public interface CartRepository {

    void add(Cart cart);

    boolean findBy(CartId cartId);

    ArrayList<Product> getBy(CartId cartId);
}
