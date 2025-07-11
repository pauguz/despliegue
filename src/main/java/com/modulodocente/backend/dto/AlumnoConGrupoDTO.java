package com.modulodocente.backend.dto;

public class AlumnoConGrupoDTO {

    private Long alumnoId;
    private String codigo;
    private String nombres;
    private String apellidos;
    private String email;
    private Integer grupoId;
    private String grupoCodigo;

    // Constructor vacío
    public AlumnoConGrupoDTO() {}

    // Constructor con todos los campos
    public AlumnoConGrupoDTO(Long alumnoId, String codigo, String nombres, String apellidos,
                             String email, Integer grupoId, String grupoCodigo) {
        this.alumnoId = alumnoId;
        this.codigo = codigo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.grupoId = grupoId;
        this.grupoCodigo = grupoCodigo;
    }

    // Getters y Setters
    public Long getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(Long alumnoId) {
        this.alumnoId = alumnoId;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(Integer grupoId) {
        this.grupoId = grupoId;
    }

    public String getGrupoCodigo() {
        return grupoCodigo;
    }

    public void setGrupoCodigo(String grupoCodigo) {
        this.grupoCodigo = grupoCodigo;
    }
}
