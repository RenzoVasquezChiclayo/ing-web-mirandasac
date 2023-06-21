package com.proyecto_web.ing_web.servicios;

import java.sql.Array;
import java.util.List;

import org.springframework.stereotype.Component;

import com.proyecto_web.ing_web.entities.Persona;

@Component
public interface PersonaService {
    
    public Boolean findPersonaByRuc_Dni(String ruc_dni);

    public Persona findLastIdPersona(String ruc_dni);

    public void guardar(Persona persona);

}
