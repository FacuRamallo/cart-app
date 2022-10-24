package com.backend.cartapp.infrastructure.configuration;

import com.backend.cartapp.application.CreateCart;
import com.backend.cartapp.domain.contracts.CartRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    CreateCart createCart(CartRepository cartRepository) {
        return new CreateCart(cartRepository);
    }
}
