package com.proyecto_web.ing_web.entities;

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

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @Column(name = "createdAt", nullable = false)
    private Date createdAt;

    @Column(name = "updatedAt", nullable = false)
    private Date updatedAt;

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

    public almacen_productos(Integer id, Integer stock, Double precio, Date createdAt, Date updatedAt,
            Empleado empleadoId, Proveedor proveedorId, Producto productoId, Almacen almacenId) {
        this.id = id;
        this.stock = stock;
        this.precio = precio;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
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
