package com.proyecto_web.ing_web.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyecto_web.ing_web.entities.Proveedor;

@Repository
public interface IProveedorRepo extends JpaRepository<Proveedor,Integer>{
    
    @Query(value = "SELECT p FROM Proveedor p JOIN FETCH p.personaId")
    public List<Proveedor> lista_proveedores();
}
