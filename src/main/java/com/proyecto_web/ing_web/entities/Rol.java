package com.proyecto_web.ing_web.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "rol")
@Entity
public class Rol {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_rol;

    @Column(name = "descripcion")
    private String descripcion;

    public Rol(){
        super();
    }

    public Rol(Integer id_rol, String descripcion){
        super();
        this.id_rol = id_rol;
        this.descripcion = descripcion;
    }

    public Rol(String descripcion){
        super();
        this.descripcion = descripcion;
    }

    public Integer getId_Rol(){
        return id_rol;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public void setId_Rol(Integer id_rol){
        this.id_rol = id_rol;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
}
