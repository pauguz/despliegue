package com.modulodocente.backend.application.service;

import com.modulodocente.backend.domain.model.Usuario;
import com.modulodocente.backend.infrastructure.mapper.UsuarioTable;
import com.modulodocente.backend.infrastructure.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Mono<ResponseEntity<String>> login(String email, String password) {
        return usuarioRepository.findByUsername(email)
            .map(UsuarioTable::toDomainModel)
            .flatMap(usuario -> {
                if (usuario.getPassword().equals(password)) {
                    return Mono.just(ResponseEntity.ok("Login exitoso"));
                } else {
                    return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta"));
                }
            })
            .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado")));
    }
}
