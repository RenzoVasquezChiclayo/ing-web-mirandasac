package com.proyecto_web.ing_web.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto_web.ing_web.entities.almacen_productos;

@Repository
public interface IAlmacen_producto extends JpaRepository<almacen_productos,Integer>{
    
}
