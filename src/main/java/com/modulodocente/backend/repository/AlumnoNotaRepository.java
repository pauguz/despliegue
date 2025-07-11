package com.modulodocente.backend.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.modulodocente.backend.domain.model.AlumnoNota;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
import java.math.BigDecimal;

public interface AlumnoNotaRepository extends ReactiveCrudRepository<AlumnoNota, Long> {

    // Listar notas por curso
    @Query("SELECT * FROM alumnonotas WHERE cursoid = :cursoId")
    Flux<AlumnoNota> findByCursoId(Integer cursoId);

    // Listar notas por alumno y curso
    @Query("SELECT * FROM alumnonotas WHERE alumnoid = :alumnoId AND cursoid = :cursoId")
    Flux<AlumnoNota> findByAlumnoIdAndCursoId(Integer alumnoId, Integer cursoId);

    // Buscar nota específica por alumno, curso y componente
    @Query("SELECT * FROM alumnonotas WHERE alumnoid = :alumnoId AND cursoid = :cursoId AND componentenotaid = :componenteNotaId")
    Mono<AlumnoNota> findByAlumnoIdAndCursoIdAndComponenteNotaId(Integer alumnoId, Integer cursoId, Integer componenteNotaId);

    // Actualizar nota
    @Query("UPDATE alumnonotas SET nota = :nota WHERE alumnoid = :alumnoId AND cursoid = :cursoId AND componentenotaid = :componenteNotaId")
    Mono<Integer> updateNota(Integer alumnoId, Integer cursoId, Integer componenteNotaId, BigDecimal nota);

    // Insertar o actualizar nota (upsert)
    @Query("""
        INSERT INTO alumnonotas (alumnoid, cursoid, componentenotaid, nota) 
        VALUES (:alumnoId, :cursoId, :componenteNotaId, :nota)
        ON DUPLICATE KEY UPDATE nota = :nota
    """)
    Mono<Integer> insertOrUpdateNota(Integer alumnoId, Integer cursoId, Integer componenteNotaId, BigDecimal nota);

    // Verificar si existe una nota
    @Query("SELECT COUNT(*) FROM alumnonotas WHERE alumnoid = :alumnoId AND cursoid = :cursoId AND componentenotaid = :componenteNotaId")
    Mono<Long> existsByAlumnoIdAndCursoIdAndComponenteNotaId(Integer alumnoId, Integer cursoId, Integer componenteNotaId);

    // Obtener promedio de un grupo
    @Query("""
        SELECT AVG(nota) 
        FROM alumnonotas an
        JOIN alumnocurso ac ON an.alumnoid = ac.alumnoid
        JOIN alumnogrupo ag ON ac.id = ag.alumnocursoid
        WHERE ag.grupoid = :grupoId AND ac.cursoid = :cursoId AND an.componentenotaid = :componenteNotaId
    """)
    Mono<Double> getPromedioByGrupoId(Integer grupoId, Integer cursoId, Integer componenteNotaId);
} 