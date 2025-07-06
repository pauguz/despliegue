package com.modulodocente.backend.presentation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class WebFluxSecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http
            .csrf().disable()  // Desactiva CSRF porque es una API REST
            .authorizeExchange()
                .pathMatchers("/api/**").permitAll()  // Permitir todo lo de /api/ sin autenticación
                .anyExchange().permitAll()            // Otras rutas también
            .and()
            .httpBasic()  // Activar autenticación básica (aunque no se usa aún)
            .and()
            .build();
    }
}
