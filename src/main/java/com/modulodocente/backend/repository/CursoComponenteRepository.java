package com.modulodocente.backend.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.modulodocente.backend.domain.model.CursoComponente;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CursoComponenteRepository extends ReactiveCrudRepository<CursoComponente, Long> {

    @Query("SELECT * FROM cursocomponente WHERE cursoid = :cursoId ORDER BY orden")
    Flux<CursoComponente> findByCursoId(Integer cursoId);

    @Query("SELECT * FROM cursocomponente WHERE cursoid = :cursoId AND nivel = :nivel ORDER BY orden")
    Flux<CursoComponente> findByCursoIdAndNivel(Integer cursoId, Integer nivel);

    @Query("SELECT * FROM cursocomponente WHERE padre = :padreId ORDER BY orden")
    Flux<CursoComponente> findByPadre(Integer padreId);

    @Query("SELECT * FROM cursocomponente WHERE codigo = :codigo AND cursoid = :cursoId")
    Mono<CursoComponente> findByCodigoAndCursoId(String codigo, Integer cursoId);


    // 3. Ver hojas de curso
    @Query("""
    SELECT * FROM cursocomponente c
    WHERE c.cursoid = :cursoId AND c.id NOT IN (
    SELECT DISTINCT padreid 
    FROM cursocomponente 
    WHERE padreid IS NOT NULL
    )
    ORDER BY c.orden
    """)
    Flux<CursoComponente> findByCursoIdAndPadreIsNull(Integer cursoId);

    @Query("SELECT * FROM cursocomponente WHERE cursoid = :cursoId AND descripcion LIKE CONCAT('%', :descripcion, '%') ORDER BY orden")
    Flux<CursoComponente> findByCursoIdAndDescripcionContaining(Integer cursoId, String descripcion);

    @Query("SELECT * FROM cursocomponente WHERE cursoid = :cursoId AND orden >= :ordenMinimo ORDER BY orden")
    Flux<CursoComponente> findByCursoIdAndOrdenMayorOIgual(Integer cursoId, Integer ordenMinimo);
} 