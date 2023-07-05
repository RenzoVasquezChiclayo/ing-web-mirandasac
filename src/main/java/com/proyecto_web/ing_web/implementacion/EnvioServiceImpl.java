package com.proyecto_web.ing_web.implementacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto_web.ing_web.entities.Envio;
import com.proyecto_web.ing_web.entities.envio_productos;
import com.proyecto_web.ing_web.repositorios.IEnvioRepo;
import com.proyecto_web.ing_web.repositorios.IEnvio_productosRepo;
import com.proyecto_web.ing_web.servicios.EnvioService;

@Service
public class EnvioServiceImpl implements EnvioService{
    
    @Autowired
    private IEnvioRepo envio_repo;

    @Autowired
    private IEnvio_productosRepo envio_prod_repo;

    public boolean guardarEnvio(Envio envio){

        try {
            envio_repo.save(envio);
            // envio_prod_repo.save(envio_productos);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
