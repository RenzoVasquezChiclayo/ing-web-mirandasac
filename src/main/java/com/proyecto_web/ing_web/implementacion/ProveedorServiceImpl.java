package com.proyecto_web.ing_web.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto_web.ing_web.entities.Proveedor;
import com.proyecto_web.ing_web.repositorios.IProveedorRepo;
import com.proyecto_web.ing_web.servicios.ProveedorService;

@Service
public class ProveedorServiceImpl implements ProveedorService{
    

    @Autowired
    private IProveedorRepo proveedor_DAO;  
    
    public Proveedor guardar(Proveedor proveedor){
        return proveedor_DAO.save(proveedor);
    }

    public List<Proveedor> listaProveedores(){

        List<Proveedor> lista_proveedor = proveedor_DAO.lista_proveedores();
        if (lista_proveedor.size() == 0) {
            System.out.println("FALSEEEEEEEEEE: "+lista_proveedor.size());
            return null;
        }else{ 
            System.out.println("TRUEEEEEEEEEEE: "+lista_proveedor.size());
            return lista_proveedor;
        }
    }
}
