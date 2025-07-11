package com.modulodocente.backend.dto;

import java.util.List;

public class NotificacionRequestDTO {

    private List<String> correos;
    private String titulo;
    private String mensaje;

    // Getters y Setters
    public List<String> getCorreos() {
        return correos;
    }

    public void setCorreos(List<String> correos) {
        this.correos = correos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
