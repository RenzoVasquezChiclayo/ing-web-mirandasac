package com.proyecto_web.ing_web.servicios;

import java.util.List;

import org.springframework.stereotype.Component;

import com.proyecto_web.ing_web.entities.Cliente;

@Component
public interface ClienteService {
    
    public Cliente guardar(Cliente cliente);

    public List<Cliente> listaClientes();
}
