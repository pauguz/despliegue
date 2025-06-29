package com.modulodocente.backend.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name= "grupo")
public class Grupo {
    @Id
    private Integer id;
    private String codigo;
    private String estado;
    private Integer institucionid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getInstitucionid() {
        return institucionid;
    }

    public void setInstitucionid(Integer institucionid) {
        this.institucionid = institucionid;
    }

}
