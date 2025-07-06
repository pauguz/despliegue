package com.modulodocente.backend.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
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
    public Mono<ResponseEntity<String>> desdeAlumnos(
            @RequestParam List<Long> alumnocursoIds,
            @RequestParam Long grupoId) {
        
        return Flux.fromIterable(alumnocursoIds)
            .flatMap(alumnocursoId -> 
                alumnoGrupoRepository.findByAlumnocursoid(alumnocursoId)
                    .flatMap(existing -> 
                        // If exists, update the grupoId
                        alumnoGrupoRepository.updateGrupoid(alumnocursoId, grupoId)
                            .thenReturn("Updated")
                    )
                    .switchIfEmpty(
                        // If doesn't exist, create new
                        alumnoGrupoRepository.save(new AlumnoGrupo(null, grupoId, alumnocursoId))
                            .thenReturn("Created")
                    )
            )
            .then(Mono.just(ResponseEntity.ok("Students added/updated successfully")))
            .onErrorResume(e -> Mono.just(
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing request: " + e.getMessage())
            ));
    }



    @PostMapping("eliminargrupo")
    public Mono<Void> eliminarGrupo(@RequestParam Long grupoId) {
        return alumnoGrupoRepository.deleteByGrupoid(grupoId)
            .then(grupoRepository.deleteById(grupoId))
            .then(); // Devuelve Mono<Void>
    }

    @PostMapping("vaciargrupo")
    public Mono<Void> vaciarGrupo(@RequestParam Long grupoId) {
        return alumnoGrupoRepository.deleteByGrupoid(grupoId)
            .then();
    }

    @GetMapping("grupos/{codigo}/{cursoId}")
    public Mono<Grupo> getGrupoByCodigoAndCursoId(@PathVariable String codigo, @PathVariable Integer cursoId) {
        return grupoRepository.findByCodigoAndCursoId(codigo, cursoId);
    }
}