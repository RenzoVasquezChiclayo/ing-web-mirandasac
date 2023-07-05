package com.proyecto_web.ing_web.servicios;

import java.util.List;

import org.springframework.stereotype.Component;

import com.proyecto_web.ing_web.entities.Conductor;

@Component
public interface ConductorService {
    
    public List<Conductor> listaConductores();
}
