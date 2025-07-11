package com.modulodocente.backend.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;
import java.math.BigDecimal;

@Table("alumnonotas")
public class AlumnoNota {

    @Id
    private Long id;

    @Column("alumnoid")
    private Integer alumnoId;

    @Column("cursoid")
    private Integer cursoId;

    @Column("componentenotaid")
    private Integer componenteNotaId;

    @Column("nota")
    private BigDecimal nota;

    // Constructor vacío
    public AlumnoNota() {}

    // Constructor con campos esenciales
    public AlumnoNota(Integer alumnoId, Integer cursoId, Integer componenteNotaId, BigDecimal nota) {
        this.alumnoId = alumnoId;
        this.cursoId = cursoId;
        this.componenteNotaId = componenteNotaId;
        this.nota = nota;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getAlumnoId() { return alumnoId; }
    public void setAlumnoId(Integer alumnoId) { this.alumnoId = alumnoId; }

    public Integer getCursoId() { return cursoId; }
    public void setCursoId(Integer cursoId) { this.cursoId = cursoId; }

    public Integer getComponenteNotaId() { return componenteNotaId; }
    public void setComponenteNotaId(Integer componenteNotaId) { this.componenteNotaId = componenteNotaId; }

    public BigDecimal getNota() { return nota; }
    public void setNota(BigDecimal nota) { this.nota = nota; }
} 