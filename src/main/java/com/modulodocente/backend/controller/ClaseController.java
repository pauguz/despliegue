package com.modulodocente.backend.controller;

import com.modulodocente.backend.domain.model.Clase;
import com.modulodocente.backend.repository.ClaseRepository;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/clases")
@CrossOrigin(origins = "http://localhost:5173")
public class ClaseController {

    private final ClaseRepository claseRepository;

    public ClaseController(ClaseRepository claseRepository) {
        this.claseRepository = claseRepository;
    }

    @GetMapping
    public Flux<Clase> getAllClases() {
        return claseRepository.findAll();
    }
}
