package com.proyecto_web.ing_web.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyecto_web.ing_web.entities.Conductor;

@Repository
public interface IConductorRepo extends JpaRepository<Conductor,Integer>{
    
    @Query(value = "SELECT c FROM Conductor c JOIN FETCH c.personaId")
    public List<Conductor> lista_conductores();
}
