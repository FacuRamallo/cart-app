package com.backend.cartapp.infrastructure.configuration;

import com.backend.cartapp.application.cartTimeToLive.DeleteCartAfterTLLScheduler;
import com.backend.cartapp.application.createCart.CreateCart;
import com.backend.cartapp.application.deleteCart.DeleteCart;
import com.backend.cartapp.application.getCart.GetCart;
import com.backend.cartapp.application.updateCart.UpdateCart;
import com.backend.cartapp.domain.contracts.CartRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Value("${ttl}")
    private Long cartsTimeToLive; //600000L 10 minutes in Milliseconds
    @Bean
    CreateCart createCart(CartRepository cartRepository, DeleteCartAfterTLLScheduler deleteCartAfterTLLScheduler) {
        return new CreateCart(cartRepository, deleteCartAfterTLLScheduler);
    }

    @Bean
    GetCart getCart(CartRepository cartRepository) {
        return new GetCart(cartRepository);
    }

    @Bean
    UpdateCart updateCart(CartRepository cartRepository) {
        return new UpdateCart(cartRepository);
    }

    @Bean
    DeleteCart deleteCart(CartRepository cartRepository) {
        return new DeleteCart(cartRepository);
    }

    @Bean
    DeleteCartAfterTLLScheduler deleteCartAfterTLLScheduler() {
        return new DeleteCartAfterTLLScheduler(cartsTimeToLive);
    }
}
