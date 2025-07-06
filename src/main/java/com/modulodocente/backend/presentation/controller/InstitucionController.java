package com.modulodocente.backend.presentation.controller;

import com.modulodocente.backend.domain.model.Institucion;
import com.modulodocente.backend.repository.InstitucionRepository;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/instituciones")
@CrossOrigin(origins = "http://localhost:5173")
public class InstitucionController {

    private final InstitucionRepository institucionRepository;

    public InstitucionController(InstitucionRepository institucionRepository) {
        this.institucionRepository = institucionRepository;
    }

    @GetMapping
    public Flux<Institucion> listarInstituciones() {
        return institucionRepository.findAll();
    }
}
