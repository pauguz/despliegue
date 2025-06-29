package com.modulodocente.backend.infrastructure.mapper;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import com.modulodocente.backend.domain.model.Usuario;


@Table("usuario") // Nombre exacto de la tabla en MySQL
public class UsuarioTable {

    @Id
    private Integer id;

    private String username;
    private String password;
    private String nombrevisualizar;
    private String estado;
    private String fechacreacion;
    private String fechavalidado;
    private String fechaultlogin;


    
    public Usuario toDomainModel() {
        Usuario usuario = new Usuario();
        usuario.setId(this.id);
        usuario.setUsername(this.username);
        usuario.setPassword(this.password);
        usuario.setNombrevisualizar(this.nombrevisualizar);
        usuario.setEstado(this.estado);
        usuario.setFechacreacion(this.fechacreacion);
        usuario.setFechavalidado(this.fechavalidado);
        usuario.setFechaultlogin(this.fechaultlogin);
        return usuario;
    }

    public static UsuarioTable fromDomainModel(Usuario usuario) {
        UsuarioTable table = new UsuarioTable();
        table.setId(usuario.getId());
        table.setUsername(usuario.getUsername());
        table.setPassword(usuario.getPassword());
        table.setNombrevisualizar(usuario.getNombrevisualizar());
        table.setEstado(usuario.getEstado());
        table.setFechacreacion(usuario.getFechacreacion());
        table.setFechavalidado(usuario.getFechavalidado());
        table.setFechaultlogin(usuario.getFechaultlogin());
        return table;
    }
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
