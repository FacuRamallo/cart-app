package com.backend.cartapp;

import com.backend.cartapp.infrastructure.Application;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(
        classes = { Application.class },
        properties = {"spring.profiles.active=integration-tests"}
)
@AutoConfigureMockMvc
public class IntegrationTest {
    @Autowired
    MockMvc mvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mvc);
    }

}
