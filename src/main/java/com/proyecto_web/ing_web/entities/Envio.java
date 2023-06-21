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
@Table(name="envio_productos")
public class Envio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fecha_recogida", nullable = false)
    private Date fecha_recogida;

    @Column(name = "direccion_recogida", nullable = false)
    private String direccion_recogida;

    @Column(name = "ciudad_recogida", nullable = false)
    private String ciudad_recogida;

    @Column(name = "pais_recogida", nullable = false)
    private String pais_recogida;

    @Column(name = "fecha_entrega", nullable = false)
    private Date fecha_entrega;

    @Column(name = "direccion_entrega", nullable = false)
    private String direccion_entrega;

    @Column(name = "ciudad_entrega", nullable = false)
    private String ciudad_entrega;

    @Column(name = "pais_entrega", nullable = false)
    private String pais_entrega;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @Column(name = "createdAt", nullable = false)
    private Date createdAt;

    @Column(name = "updatedAt", nullable = false)
    private Date updatedAt;

    @ManyToOne
    private Cliente clienteId;

    @ManyToOne
    private Ruta rutaId;

    @ManyToOne
    private Conductor conductorId;

    @ManyToOne
    private Vehiculo vehiculoId;

    public Envio() {
    }

    public Envio(Integer id, Date fecha_recogida, String direccion_recogida, String ciudad_recogida,
            String pais_recogida, Date fecha_entrega, String direccion_entrega, String ciudad_entrega,
            String pais_entrega, Boolean estado, Date createdAt, Date updatedAt, Cliente clienteId, Ruta rutaId,
            Conductor conductorId, Vehiculo vehiculoId) {
        this.id = id;
        this.fecha_recogida = fecha_recogida;
        this.direccion_recogida = direccion_recogida;
        this.ciudad_recogida = ciudad_recogida;
        this.pais_recogida = pais_recogida;
        this.fecha_entrega = fecha_entrega;
        this.direccion_entrega = direccion_entrega;
        this.ciudad_entrega = ciudad_entrega;
        this.pais_entrega = pais_entrega;
        this.estado = estado;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.clienteId = clienteId;
        this.rutaId = rutaId;
        this.conductorId = conductorId;
        this.vehiculoId = vehiculoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha_recogida() {
        return fecha_recogida;
    }

    public void setFecha_recogida(Date fecha_recogida) {
        this.fecha_recogida = fecha_recogida;
    }

    public String getDireccion_recogida() {
        return direccion_recogida;
    }

    public void setDireccion_recogida(String direccion_recogida) {
        this.direccion_recogida = direccion_recogida;
    }

    public String getCiudad_recogida() {
        return ciudad_recogida;
    }

    public void setCiudad_recogida(String ciudad_recogida) {
        this.ciudad_recogida = ciudad_recogida;
    }

    public String getPais_recogida() {
        return pais_recogida;
    }

    public void setPais_recogida(String pais_recogida) {
        this.pais_recogida = pais_recogida;
    }

    public Date getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(Date fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public String getDireccion_entrega() {
        return direccion_entrega;
    }

    public void setDireccion_entrega(String direccion_entrega) {
        this.direccion_entrega = direccion_entrega;
    }

    public String getCiudad_entrega() {
        return ciudad_entrega;
    }

    public void setCiudad_entrega(String ciudad_entrega) {
        this.ciudad_entrega = ciudad_entrega;
    }

    public String getPais_entrega() {
        return pais_entrega;
    }

    public void setPais_entrega(String pais_entrega) {
        this.pais_entrega = pais_entrega;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
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

    public Cliente getClienteId() {
        return clienteId;
    }

    public void setClienteId(Cliente clienteId) {
        this.clienteId = clienteId;
    }

    public Ruta getRutaId() {
        return rutaId;
    }

    public void setRutaId(Ruta rutaId) {
        this.rutaId = rutaId;
    }

    public Conductor getConductorId() {
        return conductorId;
    }

    public void setConductorId(Conductor conductorId) {
        this.conductorId = conductorId;
    }

    public Vehiculo getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Vehiculo vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    
}
