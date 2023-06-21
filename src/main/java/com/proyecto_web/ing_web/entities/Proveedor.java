package com.proyecto_web.ing_web.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "proveedor")
@Entity
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cta", nullable = false)
    private String cta;

    @Column(name = "fax", nullable = false)
    private String fax;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    private TipoProveedor tipo_proveedorId;

    @Column(name = "web")
    private String web;

    @Column(name = "createdAt", nullable = false)
    private Date createdAt;

    @Column(name = "updatedAt", nullable = false)
    private Date updatedAt;

    @ManyToOne
    private Empleado empleadoId;

    @ManyToOne
    private Persona personaId;

    public Proveedor() {
    }

    public Proveedor(Integer id, String cta, String fax, String observaciones, String descripcion,
            TipoProveedor tipo_proveedorId, String web, Date createdAt, Date updatedAt,
            Empleado empleadoId, Persona personaId) {
        this.id = id;
        this.cta = cta;
        this.fax = fax;
        this.observaciones = observaciones;
        this.descripcion = descripcion;
        this.tipo_proveedorId = tipo_proveedorId;
        this.web = web;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.empleadoId = empleadoId;
        this.personaId = personaId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCta() {
        return cta;
    }

    public void setCta(String cta) {
        this.cta = cta;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoProveedor getTipo_proveedorId() {
        return tipo_proveedorId;
    }

    public void setTipo_proveedorId(TipoProveedor tipo_proveedorId) {
        this.tipo_proveedorId = tipo_proveedorId;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
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

    public Persona getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Persona personaId) {
        this.personaId = personaId;
    }

    
    

}
