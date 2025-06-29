package com.modulodocente.backend.presentation.controller;

import com.modulodocente.backend.application.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<String>> login(@RequestBody Mono<Map<String, String>> loginDataMono) {
    return loginDataMono.flatMap(loginData -> {
        String email = loginData.get("email");
        String password = loginData.get("password");
        return usuarioService.login(email, password);
    });
}
}
