package com.modulodocente.backend.controller;

import com.modulodocente.backend.domain.model.Categoria;
import com.modulodocente.backend.repository.CategoriaRepository;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "http://localhost:5173")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping
    public Flux<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }
}
