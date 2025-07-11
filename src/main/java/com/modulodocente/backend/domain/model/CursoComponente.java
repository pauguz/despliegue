package com.modulodocente.backend.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "cursocomponente")
public class CursoComponente {

    @Id
    private Long id;
    private String codigo;
    private String descripcion;
    private Integer padre;
    private Integer orden;
    private Integer nivel;
    private Integer cursoid;

    public CursoComponente() {
    }

    public CursoComponente(Long id, String codigo, String descripcion, Integer padre, Integer orden, Integer nivel, Integer cursoid) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.padre = padre;
        this.orden = orden;
        this.nivel = nivel;
        this.cursoid = cursoid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPadre() {
        return padre;
    }

    public void setPadre(Integer padre) {
        this.padre = padre;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Integer getCursoid() {
        return cursoid;
    }

    public void setCursoid(Integer cursoid) {
        this.cursoid = cursoid;
    }
} 