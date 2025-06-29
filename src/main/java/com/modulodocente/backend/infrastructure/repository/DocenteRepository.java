package com.modulodocente.backend.infrastructure.repository;

import com.modulodocente.backend.infrastructure.mapper.DocenteTable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface DocenteRepository extends ReactiveCrudRepository<DocenteTable, Integer> {
    Mono<Boolean> existsByCodigo(String codigo);
}
