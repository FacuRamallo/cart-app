package com.backend.cartapp.application.cartTimeToLive;

import com.backend.cartapp.domain.CartId;
import com.backend.cartapp.domain.contracts.CartRepository;

public class DeleteCartTask implements Runnable {
    private static final long start;
    private final CartId cartId;
    private long execStart;

    private CartRepository cartRepository;

    static {
        start = System.currentTimeMillis();
    }

    public DeleteCartTask(CartId cartId, CartRepository cartRepository) {
        this.cartId = cartId;
        this.cartRepository = cartRepository;
    }

    @Override
    public void run() {
        execStart = System.currentTimeMillis();
        cartRepository.delete(cartId);
        printTaskInfo();
    }

    private void printTaskInfo() {
        String builder = "Key -> " +
                cartId.id +
                " - deleted - Exec before: " +
                (execStart - start);

        System.out.println(builder);
    }
}

