package com.badreddine.smartplane_backend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class SmartplaneBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartplaneBackendApplication.class, args);
	}

}
