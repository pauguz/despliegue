package com.modulodocente.backend.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("usuario")
public class Usuario {

    @Id
    private Integer id;

    private String username;
    private String password;
    private String nombrevisualizar;
    private String estado;
    private String fechacreacion;
    private String fechavalidado;
    private String fechaultlogin;

    // Getters y Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombrevisualizar() {
        return nombrevisualizar;
    }

    public void setNombrevisualizar(String nombrevisualizar) {
        this.nombrevisualizar = nombrevisualizar;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(String fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public String getFechavalidado() {
        return fechavalidado;
    }

    public void setFechavalidado(String fechavalidado) {
        this.fechavalidado = fechavalidado;
    }

    public String getFechaultlogin() {
        return fechaultlogin;
    }

    public void setFechaultlogin(String fechaultlogin) {
        this.fechaultlogin = fechaultlogin;
    }
}
