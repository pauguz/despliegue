package com.modulodocente.backend.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.modulodocente.backend.domain.model.Clase;

import reactor.core.publisher.Flux;

@Repository
public interface ClaseRepository extends ReactiveCrudRepository<Clase, String> {
    // Si necesitas métodos personalizados, puedes agregarlos aquí.
    Flux<Clase> findByEstado(String estado); // Ejemplo: listar por estado
}
