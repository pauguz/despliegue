package com.modulodocente.backend.presentation.controller;

import com.modulodocente.backend.domain.model.AlumnoNota;
import com.modulodocente.backend.repository.AlumnoNotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.*;
import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/api/alumno-notas")
@CrossOrigin(origins = "http://localhost:5173")
public class AlumnoNotaController {

    @Autowired
    private AlumnoNotaRepository alumnoNotaRepository;

    // Listar todas las notas
    @GetMapping("/listar")
    public Flux<AlumnoNota> listarTodasLasNotas() {
        return alumnoNotaRepository.findAll();
    }

    // Listar notas por curso
    @GetMapping("/curso/{cursoId}")
    public Flux<AlumnoNota> listarNotasPorCurso(@PathVariable Integer cursoId) {
        return alumnoNotaRepository.findByCursoId(cursoId);
    }

    // Listar notas por alumno y curso
    @GetMapping("/alumno/{alumnoId}/curso/{cursoId}")
    public Flux<AlumnoNota> listarNotasPorAlumnoYCurso(@PathVariable Integer alumnoId, @PathVariable Integer cursoId) {
        return alumnoNotaRepository.findByAlumnoIdAndCursoId(alumnoId, cursoId);
    }

    // Buscar nota específica
    @GetMapping("/alumno/{alumnoId}/curso/{cursoId}/componente/{componenteNotaId}")
    public Mono<ResponseEntity<AlumnoNota>> buscarNotaEspecifica(
            @PathVariable Integer alumnoId, 
            @PathVariable Integer cursoId, 
            @PathVariable Integer componenteNotaId) {
        return alumnoNotaRepository.findByAlumnoIdAndCursoIdAndComponenteNotaId(alumnoId, cursoId, componenteNotaId)
                .map(nota -> ResponseEntity.ok(nota))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }



    // Insertar o actualizar nota (upsert)
    @PostMapping("/guardar")
    public Mono<ResponseEntity<Map<String, Object>>> guardarNota(@RequestBody Map<String, Object> request) {
        Integer alumnoId = (Integer) request.get("alumnoId");
        Integer cursoId = (Integer) request.get("cursoId");
        Integer componenteNotaId = (Integer) request.get("componenteNotaId");
        BigDecimal nota = new BigDecimal(request.get("nota").toString());

        if (alumnoId == null || cursoId == null || componenteNotaId == null || nota == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Todos los campos son obligatorios");
            return Mono.just(ResponseEntity.badRequest().body(error));
        }

        return alumnoNotaRepository.insertOrUpdateNota(alumnoId, cursoId, componenteNotaId, nota)
                .map(rowsAffected -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("mensaje", "Nota guardada exitosamente");
                    response.put("alumnoId", alumnoId);
                    response.put("cursoId", cursoId);
                    response.put("componenteNotaId", componenteNotaId);
                    response.put("nota", nota);
                    return ResponseEntity.ok(response);
                });
    }



    // Contar notas por curso
    @GetMapping("/contar/curso/{cursoId}")
    public Mono<Long> contarNotasPorCurso(@PathVariable Integer cursoId) {
        return alumnoNotaRepository.findByCursoId(cursoId).count();
    }

    // Contar notas por alumno y curso
    @GetMapping("/contar/alumno/{alumnoId}/curso/{cursoId}")
    public Mono<Long> contarNotasPorAlumnoYCurso(@PathVariable Integer alumnoId, @PathVariable Integer cursoId) {
        return alumnoNotaRepository.findByAlumnoIdAndCursoId(alumnoId, cursoId).count();
    }
} 