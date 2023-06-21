package com.proyecto_web.ing_web.implementacion;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;

import org.apache.tomcat.util.json.JSONFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.proyecto_web.ing_web.entities.Persona;
import com.proyecto_web.ing_web.repositorios.IPersonaRepo;
import com.proyecto_web.ing_web.servicios.PersonaService;

@Service
public class PersonaServiceImpl implements PersonaService{
    
    @Autowired
    private IPersonaRepo persona_DAO;

    public Boolean findPersonaByRuc_Dni(String ruc_dni){
        Persona find_persona = persona_DAO.findByRUCDNI(ruc_dni);
        if (find_persona != null) {
            return true;
        }
        return false;
    }

    public Persona findLastIdPersona(String ruc_dni){
        Persona find_persona = persona_DAO.findByRUCDNI(ruc_dni);
        if (find_persona != null) {
            return find_persona;
        }
        return null;
    }

    public void guardar(Persona persona){
        persona_DAO.save(persona);
    }

}
