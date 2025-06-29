package com.modulodocente.backend.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.modulodocente.backend.domain.model.Categoria;

public interface CategoriaRepository extends ReactiveCrudRepository<Categoria, String> {
}