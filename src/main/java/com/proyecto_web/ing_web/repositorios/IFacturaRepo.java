package com.proyecto_web.ing_web.repositorios;

import com.proyecto_web.ing_web.entities.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFacturaRepo extends JpaRepository<Factura,Integer> {
}
