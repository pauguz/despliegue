package com.modulodocente.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class TestController {

    @GetMapping("/api/hello")
    public Mono<String> sayHello() {
        return Mono.just("¡El backend reactivo está funcionando correctamente!");
    }
}
