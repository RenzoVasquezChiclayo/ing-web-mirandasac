package com.proyecto_web.ing_web.implementacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto_web.ing_web.entities.Empleado;
import com.proyecto_web.ing_web.repositorios.IEmpleadoRepo;
import com.proyecto_web.ing_web.servicios.EmpleadoService;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {
    
    @Autowired
    private IEmpleadoRepo empleado_rep;

    @Override
	public Empleado guardar(Empleado empleado) {
		Empleado newempleado;
		newempleado = new Empleado(empleado.getNombres(),empleado.getApellidos(),empleado.getDni(),empleado.getCorreo(),empleado.getTelefono(),empleado.getDireccion());

		return empleado_rep.save(newempleado);
	}
}
