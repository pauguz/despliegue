package com.modulodocente.backend.presentation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.web.reactive.function.client.WebClient;


@RestController
@RequestMapping("/api/test-db")
public class DBTestController {

    private final DatabaseClient databaseClient;

    private final WebClient webClient;

    public DBTestController(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    @GetMapping
    public Mono<String> testConnection() {
        return databaseClient.sql("SELECT 1").fetch().rowsUpdated()
            .map(count -> "Conexión exitosa con la base de datos")
            .onErrorResume(e -> Mono.just("Fallo conexión DB: " + e.getMessage()));
    }

    @GetMapping("/ip")
    public Mono<String> getIp() {
    return WebClient.create("https://api.ipify.org")
        .get()
        .retrieve()
        .bodyToMono(String.class);
}
}