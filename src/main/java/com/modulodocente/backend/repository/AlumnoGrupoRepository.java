package com.modulodocente.backend.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import com.modulodocente.backend.domain.model.AlumnoGrupo;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;


import com.modulodocente.backend.domain.model.AlumnoGrupo;
//recuerda que ambos elementos son not null
public interface AlumnoGrupoRepository extends ReactiveCrudRepository<AlumnoGrupo, Long> {


    // Derived query method - no need for @Query
    Mono<Boolean> existsByAlumnocursoid(Long alumnocursoid);
    
    @Query("SELECT * FROM alumnogrupo WHERE alumnogrupo.alumnocursoid = :alumnocursoid")
    Mono<AlumnoGrupo> findByAlumnocursoid(Long alumnocursoid);

    @Query("UPDATE alumnogrupo SET grupoid = :grupoId WHERE alumnocursoid = :alumnocursoId")
    Mono<Integer> updateGrupoid(Long alumnocursoId, 
                               Long grupoId);

    @Query("DELETE FROM alumnogrupo WHERE alumnocursoid = :alumnocursoid")
    Mono<Integer> deleteByAlumnocursoid(Long alumnocursoid);

    @Query("DELETE FROM alumnogrupo WHERE grupoid = :grupoid")
    Mono<Integer> deleteByGrupoid(Long grupoid);

}
