package com.proyecto_web.ing_web.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto_web.ing_web.entities.Usuario;

public interface IUsuarioRepo extends JpaRepository<Usuario,Integer>{
    
    Usuario findByUsuario(String username);
}
