package com.backend.cartapp.infrastructure.configuration;

import com.backend.cartapp.application.createCart.CreateCart;
import com.backend.cartapp.application.deleteCart.DeleteCart;
import com.backend.cartapp.application.getCart.GetCart;
import com.backend.cartapp.application.updateCart.UpdateCart;
import com.backend.cartapp.domain.contracts.CartRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    CreateCart createCart(CartRepository cartRepository) {
        return new CreateCart(cartRepository);
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
}
