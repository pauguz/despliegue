package com.modulodocente.backend.repository;

import java.util.List;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.modulodocente.backend.domain.model.Grupo;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@Repository
public interface GrupoRepository extends ReactiveCrudRepository<Grupo, Integer> {

    Mono<Grupo> findByCodigo(String codigo);


    @Query("""
        SELECT DISTINCT g.*
        FROM grupo g
        JOIN alumnogrupo ag ON g.id = ag.grupoid
        JOIN alumnocurso ac ON ag.alumnocursoid = ac.id
        WHERE ac.cursoid = :cursoId
    """)  
    Flux<Grupo> findByCursoId(Integer cursoId);

    @Query("""
        SELECT g.*
        FROM grupo g
        JOIN alumnogrupo ag ON g.id = ag.grupoid
        JOIN alumnocurso ac ON ag.alumnocursoid = ac.id
        WHERE ac.alumnoid = :alumnoId AND ac.cursoid = :cursoId
    """)
    Mono<Grupo> findByAlumnoIdAndCursoId(Integer alumnoId, Integer cursoId);

    @Query("""
        SELECT DISTINCT g.*
        FROM grupo g
        WHERE g.id IN (:grupoIds)
    """)
    Flux<Grupo> findByGrupoIds(List<Integer> grupoIds);

    @Query("""
        SELECT COUNT(ag.id)
        FROM alumnogrupo ag
        WHERE ag.grupoid = :grupoId
    """)
    Mono<Integer> countAlumnosByGrupoId(Integer grupoId);

    @Query("""
        DELETE FROM alumnogrupo
        WHERE grupoid = :grupoId
    """)
    Mono<Void> deleteById(Long grupoId);

    @Query("""
        SELECT DISTINCT g.*
        FROM grupo g
        JOIN alumnogrupo ag ON g.id = ag.grupoid
        JOIN alumnocurso ac ON ag.alumnocursoid = ac.id
        WHERE g.codigo = :codigo AND ac.cursoid = :cursoId
    """)
    Mono<Grupo> findByCodigoAndCursoId(String codigo, Integer cursoId);

}
