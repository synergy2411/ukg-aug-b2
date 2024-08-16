package com.synergybank.gateway_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayServerApplication {

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder){
		return builder.routes().
				route(p -> p
						.path("/synergybank/accounts/**")
						.filters(f -> f.rewritePath("/synergybank/accounts/(?<segment>.*)", "/${segment}"))
						.uri("lb://ACCOUNTS")
				).
				route(p -> p
						.path("/synergybank/cards/**")
						.filters(f -> f.rewritePath("/synergybank/cards/(?<segment>.*)", "/${segment}"))
						.uri("lb://CARDS")
				).route(p -> p
						.path("/synergybank/loans/**")
						.filters(f -> f.rewritePath("/synergybank/loans/(?<segment>.*)", "/${segment}"))
						.uri("lb://LOANS")
				).
				build();
	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayServerApplication.class, args);
	}

}
