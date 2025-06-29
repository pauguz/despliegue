package com.modulodocente.backend.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

@Table("alumnogrupo")
public class AlumnoGrupo {

    @Id
    private Long id;

    private Long alumnocursoid;

    private Long grupoid;

    // Constructor por defecto
    public AlumnoGrupo() {
    }

    // Constructor con parámetros
    public AlumnoGrupo(Long id, Long grupoid, Long alumnocursoid) {
        this.id = id;
        this.grupoid = grupoid;
        this.alumnocursoid = alumnocursoid;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setAlumnocursoid(Long alumnocursoid) {
        this.alumnocursoid = alumnocursoid;
    }

    public Long getAlumnocursoid() {
        return alumnocursoid;
    }
    
    public void setGrupoid(Long grupoid) {
        this.grupoid = grupoid;
    }

    public Long getGrupoid() {
        return grupoid;
    }
}
