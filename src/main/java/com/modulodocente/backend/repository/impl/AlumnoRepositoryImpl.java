package com.modulodocente.backend.repository.impl;

import com.modulodocente.backend.dto.AlumnoConGrupoDTO;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public class AlumnoRepositoryImpl {

    private final DatabaseClient databaseClient;

    public AlumnoRepositoryImpl(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    public Flux<AlumnoConGrupoDTO> obtenerAlumnosConGrupoPorCurso(Integer cursoId) {
        String sql = """
            SELECT 
                a.id AS alumno_id,
                a.codigo,
                a.nombres,
                a.apellidos,
                a.email,
                g.id AS grupo_id,
                g.codigo AS grupo_codigo
            FROM alumno a
            JOIN alumnocurso ac ON a.id = ac.alumnoid
            LEFT JOIN alumnogrupo ag ON ac.id = ag.alumnocursoid
            LEFT JOIN grupo g ON ag.grupoid = g.id
            WHERE ac.cursoid = :cursoId
        """;

        return databaseClient.sql(sql)
                .bind("cursoId", cursoId)
                .map((row, metadata) -> new AlumnoConGrupoDTO(
                        row.get("alumno_id", Long.class),
                        row.get("codigo", String.class),
                        row.get("nombres", String.class),
                        row.get("apellidos", String.class),
                        row.get("email", String.class),
                        row.get("grupo_id", Integer.class),
                        row.get("grupo_codigo", String.class)
                ))
                .all();
    }
}
