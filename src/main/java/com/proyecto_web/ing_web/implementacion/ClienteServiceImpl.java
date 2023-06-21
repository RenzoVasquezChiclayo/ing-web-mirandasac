package com.proyecto_web.ing_web.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto_web.ing_web.entities.Cliente;
import com.proyecto_web.ing_web.entities.Persona;
import com.proyecto_web.ing_web.repositorios.IClienteRepo;
import com.proyecto_web.ing_web.repositorios.IPersonaRepo;
import com.proyecto_web.ing_web.servicios.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService{
    

    @Autowired
    private IClienteRepo clienteDAO;

    @Override
	public Cliente guardar(Cliente cliente) {
		return clienteDAO.save(cliente);
	}

    @Override
    public List<Cliente> listaClientes(){
        
        List<Cliente> lista_cliente = clienteDAO.lista_clientes();
        if (lista_cliente.size() == 0) {
            System.out.println("FALSEEEEEEEEEE: "+lista_cliente.size());
            return null;
        }else{ 
            System.out.println("TRUEEEEEEEEEEE: "+lista_cliente.size());
            return lista_cliente;
        }
    }
}
