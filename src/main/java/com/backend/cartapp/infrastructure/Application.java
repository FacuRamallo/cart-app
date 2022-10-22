package com.backend.cartapp.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
	scanBasePackages = {
		"com.backend.cartapp.infrastructure.controller",
		"com.backend.cartapp.infrastructure.configuration"
	}
)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
