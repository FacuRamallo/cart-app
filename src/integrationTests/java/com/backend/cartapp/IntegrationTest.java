package com.backend.cartapp;

import com.backend.cartapp.application.cartTimeToLive.DeleteCartAfterTLLScheduler;
import com.backend.cartapp.application.createCart.CreateCart;
import com.backend.cartapp.configuration.IntegrationTestConfiguration;
import com.backend.cartapp.domain.contracts.CartRepository;
import com.backend.cartapp.infrastructure.Application;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.mockMvc;
@SpringBootTest(
        classes = { Application.class },
        properties = {"spring.profiles.active=integration-tests"}
)
@AutoConfigureMockMvc
@ContextConfiguration(classes = IntegrationTestConfiguration.class)
@TestPropertySource(locations = "/application-integration-tests.properties")
public class IntegrationTest {

    @Autowired
    public Environment env;
    @Autowired
    public MockMvc mvc;

    @Autowired
    public CreateCart createCart;

    @Autowired
    public CartRepository cartRepository;

    public ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    @Qualifier("deleteCartSchedulerForTest")
    DeleteCartAfterTLLScheduler deleteCartAfterTLLScheduler;

    @BeforeEach
    void setUp() {
        mockMvc(mvc);
        cartRepository.truncate();
    }

}

