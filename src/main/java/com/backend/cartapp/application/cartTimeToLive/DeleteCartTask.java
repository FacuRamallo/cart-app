package com.backend.cartapp.application.cartTimeToLive;

import com.backend.cartapp.domain.CartId;
import com.backend.cartapp.domain.contracts.CartRepository;

public class DeleteCartTask implements Runnable {
    private final long start;
    private final CartId cartId;
    private long execStart;

    private CartRepository cartRepository;

    public DeleteCartTask(CartId cartId, CartRepository cartRepository) {
        this.start = System.currentTimeMillis();
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
        String info = "Key -> " + cartId.id + " - deleted after: " + (execStart - start)/1000 +" seg.";
        System.out.println(info);
    }
}

