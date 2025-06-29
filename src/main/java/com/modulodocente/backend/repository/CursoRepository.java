package com.modulodocente.backend.repository;
//provisional
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.modulodocente.backend.domain.model.Curso;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CursoRepository extends ReactiveCrudRepository<Curso, Integer> {

    @Query("SELECT id, nombre, codigo FROM curso WHERE id = :id")
    Mono<Curso> findById(Integer id);

    @Query("SELECT id, nombre, codigo FROM curso WHERE LOWER(nombre) LIKE LOWER(CONCAT(:parte, '%'))")
    Flux<Curso> findByNombreContaining(String parteNombre);

}
