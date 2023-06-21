package com.proyecto_web.ing_web.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipo_proveedor")
public class TipoProveedor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_tipo_proveedor;

    @Column(name = "descripcion")
    private String descripcion;

    public TipoProveedor() {
    }

    public TipoProveedor(Integer id_tipo_proveedor, String descripcion) {
        this.id_tipo_proveedor = id_tipo_proveedor;
        this.descripcion = descripcion;
    }

    public Integer getId_tipo_proveedor() {
        return id_tipo_proveedor;
    }

    public void setId_tipo_proveedor(Integer id_tipo_proveedor) {
        this.id_tipo_proveedor = id_tipo_proveedor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
}
