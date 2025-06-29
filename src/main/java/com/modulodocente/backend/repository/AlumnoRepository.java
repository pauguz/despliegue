package com.modulodocente.backend.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.modulodocente.backend.domain.model.Alumno;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

public interface AlumnoRepository extends ReactiveCrudRepository<Alumno, Long> {

    Mono<Boolean> existsByCodigo(String codigo);
    Mono<Boolean> existsByEmail(String email);

    Mono<Alumno> findByCodigo(String codigo);
    Mono<Alumno> findByEmail(String email);

    Mono<Long> countByEstado(String estado);
    Flux<Alumno> findByInstitucionid(Integer institucionId);

    @Query("""
        SELECT DISTINCT a.*
        FROM alumno a
        JOIN alumnogrupo ag ON a.id = ag.alumnoid
        WHERE ag.grupoid = :grupoId
    """)
    Flux<Alumno> findByGrupoId(Integer grupoId);

    @Query("""
        SELECT a.*
        FROM alumno a
        JOIN alumnocurso ac ON a.id = ac.alumnoid
        JOIN alumnogrupo ag ON ac.id = ag.alumnocursoid
        JOIN grupo g ON ag.grupoid = g.id
        WHERE ac.cursoid = :cursoId
        ORDER BY g.codigo ASC
        
    """)
    Flux<Alumno> findByCursoId(Integer cursoId);
}
