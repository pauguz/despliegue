package com.modulodocente.backend.dto;

import com.modulodocente.backend.domain.model.Docente;
import com.modulodocente.backend.domain.model.Usuario;

public class RegistroDocenteDTO {
    private Usuario usuario;
    private Docente docente;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }
}