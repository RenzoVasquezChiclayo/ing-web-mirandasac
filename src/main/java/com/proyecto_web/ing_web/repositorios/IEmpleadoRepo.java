package com.proyecto_web.ing_web.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto_web.ing_web.entities.Empleado;

@Repository
public interface IEmpleadoRepo extends JpaRepository<Empleado,Integer> {
    
    public Empleado findByCorreo(String dni);
}
