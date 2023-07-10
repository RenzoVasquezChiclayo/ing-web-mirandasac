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

@Table(name = "ruta")
@Entity
public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "destino", nullable = false)
    private String destino;

    @Column(name = "distancia", nullable = false)
    private Double distancia;

    @Column(name = "duracion_estimada", nullable = false)
    private Double duracion_estimada;

    @Column(name = "tarifa_base", nullable = false)
    private Double tarifa_base;

    @Column(name = "createdAt", nullable = false)
    private LocalDate createdAt;

    @Column(name = "updatedAt", nullable = false)
    private LocalDate updatedAt;

    @ManyToOne
    private Empleado empleadoId;

    @ManyToOne
    private Almacen almacenId;


    public Ruta() {
    }


    public Ruta(Integer id, String destino, Double distancia, Double duracion_estimada, Double tarifa_base,
    LocalDate createdAt, LocalDate updatedAt, Empleado empleadoId, Almacen almacenId) {
        this.id = id;
        this.destino = destino;
        this.distancia = distancia;
        this.duracion_estimada = duracion_estimada;
        this.tarifa_base = tarifa_base;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.empleadoId = empleadoId;
        this.almacenId = almacenId;
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getDestino() {
        return destino;
    }


    public void setDestino(String destino) {
        this.destino = destino;
    }


    public Double getDistancia() {
        return distancia;
    }


    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }


    public Double getDuracion_estimada() {
        return duracion_estimada;
    }


    public void setDuracion_estimada(Double duracion_estimada) {
        this.duracion_estimada = duracion_estimada;
    }


    public Double getTarifa_base() {
        return tarifa_base;
    }


    public void setTarifa_base(Double tarifa_base) {
        this.tarifa_base = tarifa_base;
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


    public Almacen getAlmacenId() {
        return almacenId;
    }


    public void setAlmacenId(Almacen almacenId) {
        this.almacenId = almacenId;
    }

    

    

}
