package com.proyecto_web.ing_web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto_web.ing_web.entities.Empleado;
import com.proyecto_web.ing_web.entities.Usuario;
import com.proyecto_web.ing_web.implementacion.EmpleadoServiceImpl;
import com.proyecto_web.ing_web.repositorios.IEmpleadoRepo;
import com.proyecto_web.ing_web.servicios.EmpleadoService;
import com.proyecto_web.ing_web.servicios.UserService;

@Controller
@RequestMapping("/sistema")
public class InicioController {

    @Autowired
    private IEmpleadoRepo empleado_repo;

    @Autowired
    private EmpleadoService empleado_service;
    
    @Autowired
    private UserService usuario_service;

    @GetMapping("/inicio")
    public String inicio(){
        return "sistema/inicio.html";
    }

    @GetMapping("/lista-empleados")
    public String lista_empleados(){
        return "sistema/empleados/lista_empleados";
    }

    @GetMapping("/ver-agregar-empleado")
    public String ver_agregar_empleado(Model model){
        Empleado empleado = new Empleado();
        model.addAttribute("empleado",empleado);
        return "sistema/empleados/nuevo_empleado";
    }

    @PostMapping("/agregar-empleado")
    public String agregar_empleado(@ModelAttribute("empleado") Empleado empleado,
        BindingResult bindingResult,
        RedirectAttributes redirectAttrs){
            if (bindingResult.hasErrors()) {
                redirectAttrs
                    .addFlashAttribute("mensaje", "Error")
                    .addFlashAttribute("clase", "warning");
                return "redirect:/sistema/agregar-empleado";
            }
            Empleado empleadoFind = empleado_repo.findByCorreo(empleado.getCorreo());
            if (empleadoFind != null) {
                redirectAttrs
                    .addFlashAttribute("mensaje", "Ese empleado ya se encuentra registrado")
                    .addFlashAttribute("clase", "warning");
                return "redirect:/sistema/agregar-empleado";
            }
            Usuario new_usuario = new Usuario();
            new_usuario.setUsuario(empleado.getCorreo());
            new_usuario.setPassword(empleado.getCorreo());
            empleado_service.guardar(empleado);
            usuario_service.guardar(new_usuario);
            redirectAttrs
                .addFlashAttribute("mensaje", "Agregado correctamente")
                .addFlashAttribute("clase", "success");
        
            return "redirect:/sistema/lista-empleados";
    }

    @GetMapping("/lista-clientes")
    public String lista_cliente(){
        return "sistema/clientes/lista_clientes";
    }

    @GetMapping("/lista-proveedores")
    public String proveedores(){
        return "sistema/proveedores/lista_proveedores";
    }

    

    
}
