package com.modulodocente.backend.infrastructure.repository;

import com.modulodocente.backend.infrastructure.mapper.UsuarioTable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UsuarioRepository extends ReactiveCrudRepository<UsuarioTable, Integer> {

    Mono<UsuarioTable> findByUsername(String username);
    Mono<Boolean> existsByUsername(String username);
}
