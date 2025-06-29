package com.modulodocente.backend.application.service;

import com.modulodocente.backend.domain.model.Docente;
import com.modulodocente.backend.domain.model.Usuario;
import com.modulodocente.backend.dto.DocenteRequestDTO;
import com.modulodocente.backend.infrastructure.mapper.DocenteTable;
import com.modulodocente.backend.infrastructure.mapper.UsuarioTable;
import com.modulodocente.backend.infrastructure.repository.DocenteRepository;
import com.modulodocente.backend.infrastructure.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class DocenteService {

    private final UsuarioRepository usuarioRepository;
    private final DocenteRepository docenteRepository;

    public DocenteService(UsuarioRepository usuarioRepository, DocenteRepository docenteRepository) {
        this.usuarioRepository = usuarioRepository;
        this.docenteRepository = docenteRepository;
    }

    public Mono<ResponseEntity<String>> registrarDocente(DocenteRequestDTO request) {

        if (!request.password.equals(request.confirmPassword)) {
            return Mono.just(ResponseEntity.badRequest().body("Las contraseñas no coinciden."));
        }

        if (!request.email.endsWith("@unmsm.edu.pe")) {
            return Mono.just(ResponseEntity.badRequest().body("El correo debe terminar en @unmsm.edu.pe."));
        }

        return usuarioRepository.existsByUsername(request.email)
                .flatMap(existeUsuario -> {
                    if (existeUsuario) {
                        return Mono.just(ResponseEntity.badRequest().body("Ya existe un usuario con ese correo."));
                    }

                    return docenteRepository.existsByCodigo(request.codigo)
                            .flatMap(existeCodigo -> {
                                if (existeCodigo) {
                                    return Mono.just(ResponseEntity.badRequest().body("Ya existe un docente con ese código."));
                                }

                                UsuarioTable usuario = new UsuarioTable();
                                usuario.setUsername(request.email);
                                usuario.setPassword(request.password);
                                usuario.setNombrevisualizar(request.nombres + " " + request.apellidos);
                                usuario.setEstado("1");
                                usuario.setFechacreacion(LocalDateTime.now().toString());

                                return usuarioRepository.save(usuario)
                                        .flatMap(usuarioGuardado -> {
                                            Docente d = new Docente();
                                            d.setCodigo(request.codigo);
                                            d.setNombres(request.nombres);
                                            d.setApellidos(request.apellidos);
                                            d.setEmail(request.email);
                                            d.setEstado("1");
                                            d.setClaseid(request.claseId);
                                            d.setCategoriaid(request.categoriaId);
                                            d.setUsuarioid(usuarioGuardado.getId());
                                            d.setInstitucionid(request.institucionId);
                                            d.setDepartamentoid(request.departamentoId);

                                            DocenteTable entidad = DocenteTable.fromDomainModel(d);
                                            return docenteRepository.save(entidad)
                                                    .thenReturn(ResponseEntity.ok("Registro exitoso"));
                                        });
                            });
                });
    }
}
