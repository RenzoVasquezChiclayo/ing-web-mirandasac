package com.proyecto_web.ing_web.servicios;

import java.util.List;

import org.springframework.stereotype.Component;

import com.proyecto_web.ing_web.entities.Empleado;

@Component
public interface EmpleadoService {
    
    public Empleado guardar(Empleado empleado);

    public List<Empleado> listaEmpleados();

}
