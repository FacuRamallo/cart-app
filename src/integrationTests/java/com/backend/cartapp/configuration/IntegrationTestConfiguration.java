package com.backend.cartapp.configuration;

import com.backend.cartapp.application.cartTimeToLive.DeleteCartAfterTLLScheduler;
import com.backend.cartapp.application.createCart.CreateCart;
import com.backend.cartapp.domain.contracts.CartRepository;
import com.backend.cartapp.infrastructure.repository.InMemoryCartRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@Configuration
@Profile("integration-tests")
public class IntegrationTestConfiguration {

    @Value("${ttl}")
    private Long cartsTimeToLive;
    @Bean("deleteCartSchedulerForTest")
    DeleteCartAfterTLLScheduler deleteCartAfterTLLScheduler() {
        return new DeleteCartAfterTLLScheduler(cartsTimeToLive);
    }

    @Bean("cartRepositoryforTest")
    CartRepository cartRepository() {
        return new InMemoryCartRepository();
    }

    @Bean("createCartForTest")
    CreateCart createCart(CartRepository cartRepository, DeleteCartAfterTLLScheduler deleteCartAfterTLLScheduler) {
        return new CreateCart(cartRepository, deleteCartAfterTLLScheduler);
    }

}
