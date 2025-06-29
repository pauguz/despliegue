package com.modulodocente.backend.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import com.modulodocente.backend.domain.model.AlumnoGrupo;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;


import com.modulodocente.backend.domain.model.AlumnoGrupo;
//recuerda que ambos elementos son not null
public interface AlumnoGrupoRepository extends ReactiveCrudRepository<AlumnoGrupo, Long> {


    Mono<Boolean> existsByAlumnocursoid(Long alumnocursoid);

    @Query("UPDATE alumnogrupo SET grupoid = :grupoid WHERE alumnocursoid = :alumnocursoid")
    Mono<Void> updateGrupoid(Long id, Long grupoid);

    @Query("DELETE FROM alumnogrupo WHERE alumnocursoid = :alumnocursoid")
    Mono<Void> deleteByAlumnocursoid(Long alumnocursoid);

    @Query("DELETE FROM alumnogrupo WHERE grupoid = :grupoid")
    Mono<Void> deleteByGrupoid(Long grupoid);

}
