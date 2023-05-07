package com.proyecto_web.ing_web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    
    @GetMapping("/login")
    public String login(){
        return "login/iniciar_sesion";
    }

}
