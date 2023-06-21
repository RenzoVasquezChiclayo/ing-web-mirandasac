package com.proyecto_web.ing_web.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto_web.ing_web.entities.Empleado;
import com.proyecto_web.ing_web.repositorios.IEmpleadoRepo;
import com.proyecto_web.ing_web.repositorios.IPersonaRepo;
import com.proyecto_web.ing_web.servicios.EmpleadoService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {
    
    @Autowired
    private IEmpleadoRepo empleado_DAO;


    @Override
	public Empleado guardar(Empleado empleado) {
		return empleado_DAO.save(empleado);
	}

    @Override
    public List<Empleado> listaEmpleados(){
        
        List<Empleado> lista_empleado = empleado_DAO.lista_empleados();
        if (lista_empleado.size() == 0) {
            System.out.println("FALSEEEEEEEEEE: "+lista_empleado.size());
            return null;
        }else{ 
            System.out.println("TRUEEEEEEEEEEE: "+lista_empleado.size());
            return lista_empleado;
        }
    }

}
