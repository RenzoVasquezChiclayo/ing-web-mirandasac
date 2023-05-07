package com.proyecto_web.ing_web.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "proveedor")
@Entity
public class Proveedor {

    @Id
    private String id_proveedor;

    @Column(name = "razon_social")
    private String razon_social;

    @Column(name = "ruc")
    private String RUC;

    @Column(name = "dni")
    private String DNI;

    @Column(name = "cuenta_detraccion")
    private String cuenta_detraccion;

    @Column(name = "pais")
    private String pais;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "distrito")
    private String distrito;

    @Column(name = "provincia")
    private String provincia;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "tipo_proveedor")
    private String tipo_proveedor;

    @Column(name = "fax")
    private String fax;

    @Column(name = "web")
    private String web;

    @Column(name = "estado")
    private Integer estado;

    public Proveedor() {
        super();
    }

    public Proveedor(String id_proveedor, String razon_social, String rUC, String dNI, String cuenta_detraccion,
            String pais, String direccion, String distrito, String provincia, String departamento, String telefono,
            String tipo_proveedor, String fax, String web, Integer estado) {
        this.id_proveedor = id_proveedor;
        this.razon_social = razon_social;
        RUC = rUC;
        DNI = dNI;
        this.cuenta_detraccion = cuenta_detraccion;
        this.pais = pais;
        this.direccion = direccion;
        this.distrito = distrito;
        this.provincia = provincia;
        this.departamento = departamento;
        this.telefono = telefono;
        this.tipo_proveedor = tipo_proveedor;
        this.fax = fax;
        this.web = web;
        this.estado = estado;
    }

    public String getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(String id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public String getRUC() {
        return RUC;
    }

    public void setRUC(String rUC) {
        RUC = rUC;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String dNI) {
        DNI = dNI;
    }

    public String getCuenta_detraccion() {
        return cuenta_detraccion;
    }

    public void setCuenta_detraccion(String cuenta_detraccion) {
        this.cuenta_detraccion = cuenta_detraccion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo_proveedor() {
        return tipo_proveedor;
    }

    public void setTipo_proveedor(String tipo_proveedor) {
        this.tipo_proveedor = tipo_proveedor;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

}
