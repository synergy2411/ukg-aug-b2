package com.synergybank.gateway_server.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

//    @Bean
//    public SecurityWebFilterChain filterChain(ServerHttpSecurity httpSecurity){
//
//        httpSecurity.authorizeExchange(authorizeExchangeSpec ->
//                authorizeExchangeSpec
//                        .pathMatchers(HttpMethod.GET).permitAll()
//                        .pathMatchers("/synergybank/accounts/**").authenticated()
//                        .pathMatchers("/synergybank/cards/**").authenticated()
//                        .pathMatchers("/synergybank/loans/**").authenticated()
//
//        ).oauth2ResourceServer(oAuth2ResourceServerSpec ->
//                oAuth2ResourceServerSpec.jwt(Customizer.withDefaults()));
//
//        httpSecurity.csrf(csrfSpec -> csrfSpec.disable());
//
//        return httpSecurity.build();
//    }


    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity httpSecurity){

        httpSecurity.authorizeExchange(authorizeExchangeSpec ->
                authorizeExchangeSpec
                        .pathMatchers(HttpMethod.GET).permitAll()
                        .pathMatchers("/synergybank/accounts/**").hasRole("ACCOUNTS")
                        .pathMatchers("/synergybank/cards/**").hasRole("CARDS")
                        .pathMatchers("/synergybank/loans/**").hasRole("LOANS")

        ).oauth2ResourceServer(oAuth2ResourceServerSpec ->
                oAuth2ResourceServerSpec.jwt(jwtSpec ->
                        jwtSpec.jwtAuthenticationConverter(grantedAuthoritiesExtractor())));

        httpSecurity.csrf(csrfSpec -> csrfSpec.disable());

        return httpSecurity.build();
    }

    private Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthoritiesExtractor(){
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(new KeyCloakRoleConverter());
        return new ReactiveJwtAuthenticationConverterAdapter(converter);
    }
}
