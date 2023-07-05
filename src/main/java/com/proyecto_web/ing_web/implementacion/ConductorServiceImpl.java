package com.proyecto_web.ing_web.implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto_web.ing_web.entities.Conductor;
import com.proyecto_web.ing_web.repositorios.IConductorRepo;
import com.proyecto_web.ing_web.servicios.ConductorService;

@Service
public class ConductorServiceImpl implements ConductorService{
    
    @Autowired
    private IConductorRepo conductor_repo;

    public List<Conductor> listaConductores(){

        return conductor_repo.findAll();
    }
}
