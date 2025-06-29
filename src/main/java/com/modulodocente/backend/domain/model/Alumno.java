package com.modulodocente.backend.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

@Table("alumno")
public class Alumno {

    @Id
    private Long id;

    @Column("codigo")
    private String codigo;

    @Column("nombres")
    private String nombres;

    @Column("apellidos")
    private String apellidos;

    @Column("email")
    private String email;

    @Column("estado")
    private String estado;

    @Column("institucionid")
    private Integer institucionid;

    @Column("departamentoid")
    private Long departamentoid;

    @Column("usuarioid")
    private Integer usuarioid;

    // Constructor vacío
    public Alumno() {}

    // Constructor con campos esenciales
    public Alumno(String codigo, String nombres, String apellidos, String email) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.estado = "ACTIVO";
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Integer getInstitucionid() { return institucionid; }
    public void setInstitucionid(Integer institucionid) { this.institucionid = institucionid; }

    public Long getDepartamentoid() { return departamentoid; }
    public void setDepartamentoid(Long departamentoid) { this.departamentoid = departamentoid; }

    public Integer getUsuarioid() { return usuarioid; }
    public void setUsuarioid(Integer usuarioid) { this.usuarioid = usuarioid; }
}
