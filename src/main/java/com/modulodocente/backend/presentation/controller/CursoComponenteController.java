package com.modulodocente.backend.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.modulodocente.backend.domain.model.CursoComponente;
import com.modulodocente.backend.repository.CursoComponenteRepository;

@RestController
@RequestMapping("/api/cursocomponente")
@CrossOrigin(origins = "http://localhost:5173")
public class CursoComponenteController {

    @Autowired
    private CursoComponenteRepository cursoComponenteRepository;

    // 1. Consultar por cursoId
    @GetMapping("curso/{cursoId}")
    public Flux<CursoComponente> getComponentesByCursoId(@PathVariable Integer cursoId) {
        return cursoComponenteRepository.findByCursoId(cursoId);
    }

    // 2. Consultar por cursoId y nivel
    @GetMapping("curso/{cursoId}/nivel/{nivel}")
    public Flux<CursoComponente> getComponentesByCursoIdAndNivel(@PathVariable Integer cursoId, @PathVariable Integer nivel) {
        return cursoComponenteRepository.findByCursoIdAndNivel(cursoId, nivel);
    }

    // 3. Ver hojas de curso USADO EN REGISTRO DE NOTAS
    @GetMapping("curso/{cursoId}/hojas")
    public Flux<CursoComponente> getHojasByCursoId(@PathVariable Integer cursoId) {
        return cursoComponenteRepository.findByCursoIdAndPadreIsNull(cursoId);
    }

    // 4. Obtener hijos de un componente específico
    @GetMapping("padre/{padreId}")
    public Flux<CursoComponente> getComponentesByPadre(@PathVariable Integer padreId) {
        return cursoComponenteRepository.findByPadre(padreId);
    }

    // 5. Buscar componente por código y curso
    @GetMapping("buscar")
    public Mono<CursoComponente> getComponenteByCodigoAndCursoId(@RequestParam String codigo, @RequestParam Integer cursoId) {
        return cursoComponenteRepository.findByCodigoAndCursoId(codigo, cursoId);
    }

    // 6. Buscar componentes por descripción en un curso
    @GetMapping("curso/{cursoId}/buscar")
    public Flux<CursoComponente> getComponentesByCursoIdAndDescripcion(@PathVariable Integer cursoId, @RequestParam String descripcion) {
        return cursoComponenteRepository.findByCursoIdAndDescripcionContaining(cursoId, descripcion);
    }

    // 7. Obtener componentes desde un orden específico
    @GetMapping("curso/{cursoId}/desde-orden/{ordenMinimo}")
    public Flux<CursoComponente> getComponentesDesdeOrden(@PathVariable Integer cursoId, @PathVariable Integer ordenMinimo) {
        return cursoComponenteRepository.findByCursoIdAndOrdenMayorOIgual(cursoId, ordenMinimo);
    }


    // 8. Obtener componente por ID
    @GetMapping("{id}")
    public Mono<ResponseEntity<CursoComponente>> getComponenteById(@PathVariable Long id) {
        return cursoComponenteRepository.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
} 