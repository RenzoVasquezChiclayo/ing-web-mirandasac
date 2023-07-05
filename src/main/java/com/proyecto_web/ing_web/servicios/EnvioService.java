package com.proyecto_web.ing_web.servicios;

import org.springframework.stereotype.Component;

import com.proyecto_web.ing_web.entities.Envio;
import com.proyecto_web.ing_web.entities.envio_productos;

@Component
public interface EnvioService {
    
    public boolean guardarEnvio(Envio envio);
}
