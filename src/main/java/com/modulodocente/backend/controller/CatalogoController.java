package com.modulodocente.backend.controller;

import com.modulodocente.backend.domain.model.Categoria;
import com.modulodocente.backend.domain.model.Departamento;
import com.modulodocente.backend.domain.model.Institucion;
import com.modulodocente.backend.repository.CategoriaRepository;
import com.modulodocente.backend.repository.InstitucionRepository;
import com.modulodocente.backend.repository.DepartamentoRepository;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/catalogo")
@CrossOrigin(origins = "*")
public class CatalogoController {

    private final CategoriaRepository categoriaRepo;
    private final InstitucionRepository institucionRepo;
    private final DepartamentoRepository departamentoRepo;

    public CatalogoController(
        CategoriaRepository categoriaRepo,
        InstitucionRepository institucionRepo,
        DepartamentoRepository departamentoRepo
    ) {
        this.categoriaRepo = categoriaRepo;
        this.institucionRepo = institucionRepo;
        this.departamentoRepo = departamentoRepo;
    }

    @GetMapping("/categorias")
    public Flux<Categoria> listarCategorias() {
        return categoriaRepo.findAll();
    }

    @GetMapping("/instituciones")
    public Flux<Institucion> listarInstituciones() {
        return institucionRepo.findAll();
    }

    @GetMapping("/departamentos")
    public Flux<Departamento> listarDepartamentos() {
        return departamentoRepo.findAll();
    }
}
