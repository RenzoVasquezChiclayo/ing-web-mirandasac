package com.proyecto_web.ing_web.entities;

import java.time.LocalDate;
import java.util.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "almacen_productos")
public class almacen_productos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo")
    private Integer codigo;
    
    @Column(name = "stock", nullable = false)
    private Integer stock;


    @Column(name = "createdAt", nullable = false)
    private LocalDate createdAt;

    @Column(name = "updatedAt", nullable = false)
    private LocalDate updatedAt;

    @ManyToOne
    private Empleado empleadoId;

    @ManyToOne
    private Proveedor proveedorId;

    @ManyToOne
    private Producto productoId;

    @ManyToOne
    private Almacen almacenId;

    public almacen_productos() {
    }

    public almacen_productos(Integer id, Integer codigo, Integer stock, LocalDate createdAt, LocalDate updatedAt,
            Empleado empleadoId, Proveedor proveedorId, Producto productoId, Almacen almacenId) {
        this.id = id;
        this.codigo = codigo;
        this.stock = stock;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.empleadoId = empleadoId;
        this.proveedorId = proveedorId;
        this.productoId = productoId;
        this.almacenId = almacenId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Empleado getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Empleado empleadoId) {
        this.empleadoId = empleadoId;
    }

    public Proveedor getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Proveedor proveedorId) {
        this.proveedorId = proveedorId;
    }

    public Producto getProductoId() {
        return productoId;
    }

    public void setProductoId(Producto productoId) {
        this.productoId = productoId;
    }

    public Almacen getAlmacenId() {
        return almacenId;
    }

    public void setAlmacenId(Almacen almacenId) {
        this.almacenId = almacenId;
    }

    

}
