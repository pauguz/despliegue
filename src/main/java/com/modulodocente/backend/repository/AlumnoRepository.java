package com.modulodocente.backend.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.modulodocente.backend.domain.model.Alumno;
import com.modulodocente.backend.dto.AlumnoConGrupoDTO;

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
        LEFT JOIN alumnogrupo ag ON ac.id = ag.alumnocursoid
        LEFT JOIN grupo g ON ag.grupoid = g.id
        WHERE ac.cursoid = :cursoId
        ORDER BY g.codigo ASC
        
    """)
    Flux<Alumno> findByCursoId(Integer cursoId);

    @Query("""
        SELECT ac.id
        FROM alumnocurso ac
        JOIN alumno a ON ac.alumnoid = a.id
        WHERE ac.cursoid = :cursoId
        AND TRIM(a.nombres) = TRIM(:nombre) 
        AND TRIM(a.apellidos) = TRIM(:apellido)
    """)
    Mono<Long> findByNombreAndApellidoAndCursoId(String nombre, String apellido, Integer cursoId);

    @Query("""
        SELECT ac.id
        FROM alumnocurso ac
        JOIN alumno a ON ac.alumnoid = a.id
        WHERE ac.cursoid = :cursoId
        AND a.codigo = :codigo
        """)
    Mono<Long> findbyCodigoandCursoId(String codigo, Integer cursoId);


    @Query("""
        SELECT EXISTS (
            SELECT 1
            FROM alumnocurso ac
            WHERE ac.id = :alumnocursoId
            AND ac.cursoid = :cursoId
        )
    """)
    Mono<Boolean> inCurso(Integer alumnocursoId, Integer cursoId);

    @Query("""
    SELECT 
        a.id AS alumnoId,
        a.codigo AS codigo,
        a.nombres AS nombres,
        a.apellidos AS apellidos,
        a.email AS email,
        g.id AS grupoId,
        g.codigo AS grupoCodigo
        FROM alumno a
        JOIN alumnocurso ac ON a.id = ac.alumnoid
        LEFT JOIN alumnogrupo ag ON ac.id = ag.alumnocursoid
        LEFT JOIN grupo g ON ag.grupoid = g.id
        WHERE ac.cursoid = :cursoId
    """)
    Flux<AlumnoConGrupoDTO> findAlumnosConGrupoByCursoId(Integer cursoId);
}
