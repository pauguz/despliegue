package com.modulodocente.backend.presentation.controller;

import com.modulodocente.backend.application.service.DocenteService;
import com.modulodocente.backend.dto.DocenteRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/docentes")
@CrossOrigin(origins = "http://localhost:5173")
public class DocenteController {

    private final DocenteService docenteService;

    public DocenteController(DocenteService docenteService) {
        this.docenteService = docenteService;
    }

    @PostMapping("/registrar")
    public Mono<ResponseEntity<String>> registrarDocente(@RequestBody DocenteRequestDTO request) {
        return docenteService.registrarDocente(request);
    }
}
