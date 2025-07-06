package com.modulodocente.backend.presentation.controller;

import com.modulodocente.backend.domain.model.Alumno;
import com.modulodocente.backend.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.*;
import java.util.*;

@RestController
@RequestMapping("/api/alumnos")
@CrossOrigin(origins = "http://localhost:5173")
public class AlumnoController {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @PostMapping("/guardar-masivo")
    public Mono<ResponseEntity<Map<String, Object>>> guardarAlumnosMasivo(@RequestBody List<Map<String, String>> datosAlumnos) {
        List<Mono<Alumno>> guardados = new ArrayList<>();
        List<String> errores = new ArrayList<>();

        return Flux.fromIterable(datosAlumnos)
            .index()
            .flatMap(tuple -> {
                long i = tuple.getT1();
                Map<String, String> datos = tuple.getT2();
                String codigo = obtenerValor(datos, "Código", "codigo", "CODIGO", "Code");
                String nombres = obtenerValor(datos, "Nombres", "nombres", "NOMBRES", "Name", "Nombre");
                String apellidos = obtenerValor(datos, "Apellidos", "apellidos", "APELLIDOS", "LastName", "Apellido");
                String email = obtenerValor(datos, "Correo", "correo", "CORREO", "Email", "EMAIL", "email");
                String estado = obtenerValor(datos, "Estado", "estado", "ESTADO", "Status");

                if (codigo == null || codigo.isBlank()) {
                    errores.add("Fila " + (i + 2) + ": Código obligatorio");
                    return Mono.empty();
                }

                if (nombres == null || nombres.isBlank()) {
                    errores.add("Fila " + (i + 2) + ": Nombres obligatorios");
                    return Mono.empty();
                }

                if (apellidos == null || apellidos.isBlank()) {
                    errores.add("Fila " + (i + 2) + ": Apellidos obligatorios");
                    return Mono.empty();
                }

                return alumnoRepository.existsByCodigo(codigo.trim())
                    .flatMap(existeCodigo -> {
                        if (existeCodigo) {
                            errores.add("Fila " + (i + 2) + ": Código ya existe");
                            return Mono.empty();
                        }
                        return alumnoRepository.existsByEmail(email == null ? "" : email.trim())
                            .flatMap(existeEmail -> {
                                if (existeEmail) {
                                    errores.add("Fila " + (i + 2) + ": Email ya existe");
                                    return Mono.empty();
                                }

                                Alumno alumno = new Alumno();
                                alumno.setCodigo(codigo.trim());
                                alumno.setNombres(nombres.trim());
                                alumno.setApellidos(apellidos.trim());
                                alumno.setEmail(email != null ? email.trim() : null);
                                alumno.setEstado(estado != null ? estado.trim().toUpperCase() : "ACTIVO");
                                alumno.setInstitucionid(null);
                                alumno.setDepartamentoid(null);
                                alumno.setUsuarioid(null);

                                return alumnoRepository.save(alumno);
                            });
                    });
            })
            .collectList()
            .map(alumnosGuardados -> {
                Map<String, Object> response = new HashMap<>();
                response.put("exitosos", alumnosGuardados.size());
                response.put("errores", errores.size());
                response.put("total", datosAlumnos.size());
                response.put("mensajesError", errores);
                response.put("mensaje", alumnosGuardados.size() + " alumnos guardados" + (errores.size() > 0 ? ". " + errores.size() + " errores" : ""));
                return ResponseEntity.ok(response);
            });
    }

    @GetMapping("/contar")
    public Mono<Long> contarAlumnos() {
        return alumnoRepository.count();
    }

    @GetMapping("/contar-por-estado/{estado}")
    public Mono<Long> contarPorEstado(@PathVariable String estado) {
        return alumnoRepository.countByEstado(estado.toUpperCase());
    }

    @GetMapping("/listar")
    public Flux<Alumno> listarAlumnos() {
        return alumnoRepository.findAll();
    }

    private String obtenerValor(Map<String, String> datos, String... claves) {
        for (String c : claves) {
            if (datos.containsKey(c) && datos.get(c) != null) return datos.get(c);
        }
        return null;
    }

    @GetMapping("/curso/{cursoId}")
    public Flux<Alumno> listarAlumnosPorCurso(@PathVariable Integer cursoId) {
        return alumnoRepository.findByCursoId(cursoId);
    }

    @GetMapping("/nombre-apellido/{nombre}/{apellido}/{cursoId}")
    public Mono<Long> buscarAlumnoPorNombreApellido(@PathVariable String nombre, @PathVariable String apellido, @PathVariable Integer cursoId) {
        return alumnoRepository.findByNombreAndApellidoAndCursoId(nombre, apellido, cursoId);
    }

    @GetMapping("/in-curso/{alumnocursoId}/{cursoId}")
    public Mono<Boolean> inCurso(@PathVariable Integer alumnocursoId, @PathVariable Integer cursoId) {
        return alumnoRepository.inCurso(alumnocursoId, cursoId);
    }

}
