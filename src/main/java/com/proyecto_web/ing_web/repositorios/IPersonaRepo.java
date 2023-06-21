package com.proyecto_web.ing_web.repositorios;

import java.sql.Array;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyecto_web.ing_web.entities.Persona;

@Repository
public interface IPersonaRepo extends JpaRepository<Persona,Integer>{
    
    public Persona findByRUCDNI(String ruc_dni);

}
