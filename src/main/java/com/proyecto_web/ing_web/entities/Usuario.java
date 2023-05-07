package com.proyecto_web.ing_web.entities;

import java.util.Collection;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Table(name = "usuario")
@Entity
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="usuario")
    private String usuario;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
        name = "usuarios_roles",
        joinColumns = @JoinColumn(name = "id_usuario",referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "rol_id",referencedColumnName = "id_rol")
    )
    private Collection<Rol> rol;


    public Usuario() {
        super();
    }

    public Usuario(String usuario, String password, Collection<Rol> rol) {
        this.usuario = usuario;
        this.password = password;
        this.rol = rol;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Rol> getRol() {
        return rol;
    }

    public void setRol(Collection<Rol> rol) {
        this.rol = rol;
    }

    
    
}
