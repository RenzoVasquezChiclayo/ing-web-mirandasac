package com.proyecto_web.ing_web.servicios;

import java.util.List;

import org.springframework.stereotype.Component;

import com.proyecto_web.ing_web.entities.Proveedor;

@Component
public interface ProveedorService {

    public Proveedor guardar(Proveedor proveedor);
    
    public List<Proveedor> listaProveedores();
}
