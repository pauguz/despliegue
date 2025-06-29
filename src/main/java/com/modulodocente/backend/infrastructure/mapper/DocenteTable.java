package com.modulodocente.backend.infrastructure.mapper;

import com.modulodocente.backend.domain.model.Docente;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("docente") // nombre exacto de la tabla en la base de datos
public class DocenteTable {

    @Id
    private Integer id;

    private String codigo;
    private String nombres;
    private String apellidos;
    private String email;
    private String estado;
    private String claseid;
    private String categoriaid;
    private Integer usuarioid;
    private Integer institucionid;
    private Long departamentoid;

    // ==== Getters y Setters ====

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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getClaseid() {
        return claseid;
    }

    public void setClaseid(String claseid) {
        this.claseid = claseid;
    }

    public String getCategoriaid() {
        return categoriaid;
    }

    public void setCategoriaid(String categoriaid) {
        this.categoriaid = categoriaid;
    }

    public Integer getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(Integer usuarioid) {
        this.usuarioid = usuarioid;
    }

    public Integer getInstitucionid() {
        return institucionid;
    }

    public void setInstitucionid(Integer institucionid) {
        this.institucionid = institucionid;
    }

    public Long getDepartamentoid() {
        return departamentoid;
    }

    public void setDepartamentoid(Long departamentoid) {
        this.departamentoid = departamentoid;
    }

    // ==== Conversión a Modelo de Dominio ====

    public Docente toDomainModel() {
        Docente docente = new Docente();
        docente.setId(this.id);
        docente.setCodigo(this.codigo);
        docente.setNombres(this.nombres);
        docente.setApellidos(this.apellidos);
        docente.setEmail(this.email);
        docente.setEstado(this.estado);
        docente.setClaseid(this.claseid);
        docente.setCategoriaid(this.categoriaid);
        docente.setUsuarioid(this.usuarioid);
        docente.setInstitucionid(this.institucionid);
        docente.setDepartamentoid(this.departamentoid);
        return docente;
    }

    // ==== Conversión desde Modelo de Dominio ====

    public static DocenteTable fromDomainModel(Docente docente) {
        DocenteTable table = new DocenteTable();
        table.setId(docente.getId());
        table.setCodigo(docente.getCodigo());
        table.setNombres(docente.getNombres());
        table.setApellidos(docente.getApellidos());
        table.setEmail(docente.getEmail());
        table.setEstado(docente.getEstado());
        table.setClaseid(docente.getClaseid());
        table.setCategoriaid(docente.getCategoriaid());
        table.setUsuarioid(docente.getUsuarioid());
        table.setInstitucionid(docente.getInstitucionid());
        table.setDepartamentoid(docente.getDepartamentoid());
        return table;
    }
}
