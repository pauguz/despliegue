package com.modulodocente.backend.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.modulodocente.backend.domain.model.Departamento;

@Repository
public interface DepartamentoRepository extends ReactiveCrudRepository<Departamento, Long> {
}
