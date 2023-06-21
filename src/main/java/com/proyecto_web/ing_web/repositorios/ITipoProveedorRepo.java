package com.proyecto_web.ing_web.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto_web.ing_web.entities.TipoProveedor;

@Repository
public interface ITipoProveedorRepo extends JpaRepository<TipoProveedor,Integer>{
    
}
