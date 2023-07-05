package com.proyecto_web.ing_web.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto_web.ing_web.entities.envio_productos;

@Repository
public interface IEnvio_productosRepo extends JpaRepository<envio_productos,Integer>{
    
}
