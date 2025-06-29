package com.modulodocente.backend.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("departamento")
public class Departamento {

    @Id
    private Long id;

    private String codigo;
    private String nombre;
    private Integer institucionid;
    private String estado;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getInstitucionid() { return institucionid; }
    public void setInstitucionid(Integer institucionid) { this.institucionid = institucionid; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
