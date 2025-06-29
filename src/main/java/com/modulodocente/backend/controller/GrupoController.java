package com.modulodocente.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.modulodocente.backend.domain.model.AlumnoGrupo;
import com.modulodocente.backend.domain.model.Curso;
import com.modulodocente.backend.domain.model.Grupo;
import com.modulodocente.backend.repository.CursoRepository;
import com.modulodocente.backend.repository.GrupoRepository;
import com.modulodocente.backend.repository.AlumnoGrupoRepository;


@RestController
@RequestMapping("/api/grupos")
@CrossOrigin(origins = "http://localhost:5173")
public class GrupoController {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private AlumnoGrupoRepository alumnoGrupoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping("curso/{cursoId}")
    public Flux<Grupo> getGruposByCursoId(@PathVariable Integer cursoId) {
        return grupoRepository.findByCursoId(cursoId);
    }




    //Obtener grupos por alumno y curso
    @GetMapping("alumnos/{cursoId}")
    public Flux<Grupo> getGrupoByAlumnoIdAndCursoId(@RequestParam List<Integer> alumnoIds, @PathVariable Integer cursoId) {
        return Flux.fromIterable(alumnoIds)
                .flatMap(alumnoId -> grupoRepository.findByAlumnoIdAndCursoId(alumnoId, cursoId));
    }

    @GetMapping("grupos/{grupoIds}")
    public Flux<Grupo> getGruposByGrupoIds(@RequestParam List<Integer> grupoIds) {
        return grupoRepository.findByGrupoIds(grupoIds);
    }

    @GetMapping("buscarcurso/{parteNombre}")
    public Flux<Curso> getGruposByNombre(@PathVariable String parteNombre) {
        return cursoRepository.findByNombreContaining(parteNombre);
    }

    @PostMapping("creargrupo")
    public Mono<Grupo> crearGrupo(@RequestBody Grupo grupo) {
        
        return grupoRepository.save(grupo);
    }

    @PostMapping("agregaralumnos")
    public Mono<Void> desdeAlumnos(@RequestParam List<Long> alumnocursoIds,
                                   @RequestParam Long grupoId) {
        return Flux.fromIterable(alumnocursoIds)
            .flatMap(alumnocursoId ->
                alumnoGrupoRepository.existsByAlumnocursoid(alumnocursoId)
                    .flatMap(exists -> {
                        if (!exists) {
                            return alumnoGrupoRepository.save(new AlumnoGrupo(null, grupoId, alumnocursoId)).then();
                        } else {
                            return alumnoGrupoRepository.updateGrupoid(alumnocursoId, grupoId).then();
                        }
                    })
            )
            .then(); // Devuelve Mono<Void> al final
    }

    @PostMapping("eliminargrupo")
    public Mono<Void> eliminarGrupo(@RequestParam Long grupoId) {
        return alumnoGrupoRepository.deleteByGrupoid(grupoId)
            .then(grupoRepository.deleteById(grupoId))
            .then(); // Devuelve Mono<Void>
    }







    
}