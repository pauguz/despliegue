package com.modulodocente.backend.controller;

import com.modulodocente.backend.domain.model.Departamento;
import com.modulodocente.backend.repository.DepartamentoRepository;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/departamentos")
@CrossOrigin(origins = "http://localhost:5173")
public class DepartamentoController {

    private final DepartamentoRepository departamentoRepository;

    public DepartamentoController(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    @GetMapping
    public Flux<Departamento> listarDepartamentos() {
        return departamentoRepository.findAll();
    }
}
