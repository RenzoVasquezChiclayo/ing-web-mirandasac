package com.proyecto_web.ing_web.entities;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "persona")
public class Persona {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ruc_dni", nullable = false, unique = true)
    private String RUCDNI;

    @Column(name = "nombres", nullable = false)
    private String nombres;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @Column(name = "domicilio_fiscal", nullable = false)
    private String domicilio_fiscal;

    @Column(name = "distrito", nullable = false)
    private String distrito;

    @Column(name = "provincia", nullable = false)
    private String provincia;

    @Column(name = "departamento", nullable = false)
    private String departamento;

    @Column(name = "ubigeo", nullable = false)
    private String ubigeo;

    @Column(name = "pais", nullable = false)
    private String pais;

    @Column(name = "telefono", nullable = false)
    private String telefono;

    @Column(name = "contacto")
    private String contacto;

    @Column(name = "correo", nullable = false, unique = true)
    private String correo;

    @Column(name = "estado", nullable = false)
    private Integer estado;

    @Column(name = "createdAt", nullable = false)
    private LocalDate createdAt;

    @Column(name = "updatedAt", nullable = false)
    private LocalDate updatedAt;

    public Persona() {
    }

    public Persona(Integer id, String RUCDNI, String nombres, String apellidos, String domicilio_fiscal,
            String distrito, String provincia, String departamento, String ubigeo, String pais, String telefono,
            String contacto, String correo, Integer estado, LocalDate createdAt, LocalDate updatedAt) {
        this.id = id;
        this.RUCDNI = RUCDNI;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.domicilio_fiscal = domicilio_fiscal;
        this.distrito = distrito;
        this.provincia = provincia;
        this.departamento = departamento;
        this.ubigeo = ubigeo;
        this.pais = pais;
        this.telefono = telefono;
        this.contacto = contacto;
        this.correo = correo;
        this.estado = estado;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRUCDNI() {
        return RUCDNI;
    }

    public void setRUCDNI(String RUCDNI) {
        this.RUCDNI = RUCDNI;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDomicilio_fiscal() {
        return domicilio_fiscal;
    }

    public void setDomicilio_fiscal(String domicilio_fiscal) {
        this.domicilio_fiscal = domicilio_fiscal;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
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

    
}
