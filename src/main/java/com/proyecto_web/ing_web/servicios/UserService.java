package com.proyecto_web.ing_web.servicios;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.proyecto_web.ing_web.entities.Usuario;

@Component
public interface UserService extends UserDetailsService{
    
    public Usuario findByUsuario(String user);

    public Usuario guardar(Usuario user);
}
