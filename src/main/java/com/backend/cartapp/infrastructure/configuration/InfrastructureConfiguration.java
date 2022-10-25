package com.backend.cartapp.infrastructure.configuration;

import com.backend.cartapp.domain.contracts.CartRepository;
import com.backend.cartapp.domain.repository.InMemoryCartRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfrastructureConfiguration {

    @Bean
    CartRepository cartRepository() {
        return new InMemoryCartRepository();
    }
}
