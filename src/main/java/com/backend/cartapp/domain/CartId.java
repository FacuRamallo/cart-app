package com.backend.cartapp.domain;

import java.util.UUID;

public class CartId {

    public final UUID id;

    public CartId() {
        this.id = UUID.randomUUID();
    }

    public CartId(String id) {
        this.id = UUID.fromString(id);
    }
}
