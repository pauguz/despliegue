package com.modulodocente.backend.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.modulodocente.backend.domain.model.Institucion;

public interface InstitucionRepository extends ReactiveCrudRepository<Institucion, Integer> {
}
