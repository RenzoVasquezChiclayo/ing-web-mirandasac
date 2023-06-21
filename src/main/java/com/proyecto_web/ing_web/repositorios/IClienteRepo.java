package com.proyecto_web.ing_web.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyecto_web.ing_web.entities.Cliente;

@Repository
public interface IClienteRepo extends JpaRepository<Cliente,Integer>{
    
    @Query(value = "SELECT c FROM Cliente c JOIN FETCH c.personaId")
    public List<Cliente> lista_clientes();
}
