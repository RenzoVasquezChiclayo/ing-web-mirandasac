package com.proyecto_web.ing_web.repositorios;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto_web.ing_web.entities.Empleado;
import com.proyecto_web.ing_web.entities.Persona;

@Repository
public interface IEmpleadoRepo extends JpaRepository<Empleado,Integer> {
    
    // public Empleado findByCorreo(String dni);

    @Query(value = "SELECT e FROM Empleado e JOIN FETCH e.personaId")
    public List<Empleado> lista_empleados();

    @Query(value = "SELECT e FROM Empleado e JOIN FETCH e.personaId per WHERE per.correo = :correo")
    public Empleado findByCorreo(@Param("correo") String correo);
}
