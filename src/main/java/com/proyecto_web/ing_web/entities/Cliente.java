package com.proyecto_web.ing_web.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @Column(name = "id_cliente", nullable = false, length = 4)
    private String id_cliente;

    @Column(name = "ruc_dni", nullable = false)
    private String RUC_DNI;

    @Column(name = "razon_social", nullable = false)
    private String razon_social;

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

    @Column(name = "telefono")
    private String telefono;

    @ManyToOne(optional = false)
    private TipoCliente tipo_cliente;

    @Column(name = "contacto")
    private String contacto;

    @Column(name = "correo", nullable = false)
    private String correo;

    @Column(name = "web")
    private String web;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "precio_kg")
    private Float precio_kg;

    @Column(name = "precio_TN")
    private Float precio_TN;

    @Column(name = "precio_M3")
    private Float precio_M3;

    @Column(name = "estado", nullable = false)
    private Integer estado;

    public Cliente() {
        super();
    }

    public Cliente(String id_cliente, String RUC_DNI, String razon_social, String domicilio_fiscal,
            String distrito, String provincia, String departamento, String ubigeo, String telefono, TipoCliente tipo_cliente,
            String contacto, String correo, String web, String observaciones, Float precio_kg, Float precio_TN,
            Float precio_M3, Integer estado) {
        this.id_cliente = id_cliente;
        this.RUC_DNI = RUC_DNI;
        this.razon_social = razon_social;
        this.domicilio_fiscal = domicilio_fiscal;
        this.distrito = distrito;
        this.provincia = provincia;
        this.departamento = departamento;
        this.ubigeo = ubigeo;
        this.telefono = telefono;
        this.tipo_cliente = tipo_cliente;
        this.contacto = contacto;
        this.correo = correo;
        this.web = web;
        this.observaciones = observaciones;
        this.precio_kg = precio_kg;
        this.precio_TN = precio_TN;
        this.precio_M3 = precio_M3;
        this.estado = estado;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getRUC_DNI() {
        return RUC_DNI;
    }

    public void setRUC_DNI(String rUC_DNI) {
        RUC_DNI = rUC_DNI;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public TipoCliente getTipo_cliente() {
        return tipo_cliente;
    }

    public void setTipo_cliente(TipoCliente tipo_cliente) {
        this.tipo_cliente = tipo_cliente;
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

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Float getPrecio_kg() {
        return precio_kg;
    }

    public void setPrecio_kg(Float precio_kg) {
        this.precio_kg = precio_kg;
    }

    public Float getPrecio_TN() {
        return precio_TN;
    }

    public void setPrecio_TN(Float precio_TN) {
        this.precio_TN = precio_TN;
    }

    public Float getPrecio_M3() {
        return precio_M3;
    }

    public void setPrecio_M3(Float precio_M3) {
        this.precio_M3 = precio_M3;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
