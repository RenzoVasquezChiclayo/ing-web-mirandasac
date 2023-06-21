package com.proyecto_web.ing_web.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipo_cliente")
public class TipoCliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_tipo_cliente;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    public TipoCliente() {
    }

    public TipoCliente(Integer id_tipo_cliente, String descripcion) {
        this.id_tipo_cliente = id_tipo_cliente;
        this.descripcion = descripcion;
    }

    public Integer getId_tipo_cliente() {
        return id_tipo_cliente;
    }

    public void setId_tipo_cliente(Integer id_tipo_cliente) {
        this.id_tipo_cliente = id_tipo_cliente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    

    
}
