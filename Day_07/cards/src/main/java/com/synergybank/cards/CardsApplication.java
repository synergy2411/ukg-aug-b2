package com.synergybank.cards;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Cards microservice REST API Documentation",
				description = "SynergyBank Cards microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Monica Geller",
						email = "tutor@synergy.com",
						url = "https://www.synergybank.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.synergybank.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "SynergyBank Cards microservice REST API Documentation",
				url = "https://www.synergybank.com/swagger-ui.html"
		)
)
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}
}
