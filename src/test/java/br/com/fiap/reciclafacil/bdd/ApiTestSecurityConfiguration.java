package br.com.fiap.reciclafacil.bdd;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@TestConfiguration
public class ApiTestSecurityConfiguration {

    @Bean
    @Order(0)
    SecurityFilterChain apiTestSecurityFilterChain(
            HttpSecurity http) throws Exception {

        return http
            .securityMatcher("/**")
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth ->
                auth.anyRequest().permitAll()
            )
            .build();
    }
}