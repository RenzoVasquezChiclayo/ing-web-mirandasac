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
@Table(name = "conductor")
public class Conductor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "licencia", nullable = false, unique = true)
    private String licencia;

    @Column(name = "fecha_vencimiento", nullable = false)
    private Date fecha_vencimiento;

    @Column(name = "createdAt", nullable = false)
    private Date createdAt;

    @Column(name = "updatedAt", nullable = false)
    private Date updatedAt;

    @ManyToOne
    private Empleado empleadoId;

    @ManyToOne
    private Persona personaId;

    public Conductor() {
    }

    public Conductor(Integer id, String observaciones, String descripcion, String licencia, Date fecha_vencimiento,
            Date createdAt, Date updatedAt, Empleado empleadoId, Persona personaId) {
        this.id = id;
        this.observaciones = observaciones;
        this.descripcion = descripcion;
        this.licencia = licencia;
        this.fecha_vencimiento = fecha_vencimiento;
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

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public Date getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(Date fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
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
