package com.proyecto_web.ing_web.controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto_web.ing_web.entities.Cargos;
import com.proyecto_web.ing_web.entities.Cliente;
import com.proyecto_web.ing_web.entities.Empleado;
import com.proyecto_web.ing_web.entities.Persona;
import com.proyecto_web.ing_web.entities.Proveedor;
import com.proyecto_web.ing_web.entities.TipoCliente;
import com.proyecto_web.ing_web.entities.TipoProveedor;
import com.proyecto_web.ing_web.entities.Usuario;
import com.proyecto_web.ing_web.implementacion.EmpleadoServiceImpl;
import com.proyecto_web.ing_web.repositorios.ICargoRepo;
import com.proyecto_web.ing_web.repositorios.IEmpleadoRepo;
import com.proyecto_web.ing_web.repositorios.ITipoClienteRepo;
import com.proyecto_web.ing_web.repositorios.ITipoProveedorRepo;
import com.proyecto_web.ing_web.servicios.ClienteService;
import com.proyecto_web.ing_web.servicios.EmpleadoService;
import com.proyecto_web.ing_web.servicios.PersonaService;
import com.proyecto_web.ing_web.servicios.ProveedorService;
import com.proyecto_web.ing_web.servicios.UserService;

@Controller
@RequestMapping("/sistema")
public class InicioController {

    private ZoneId defaultZoneId = ZoneId.systemDefault();
    private LocalDate localDate = LocalDate.of(2016, 8, 19);
    private Date fecha = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

    @Autowired
    private ICargoRepo cargo_repo;

    @Autowired
    private ITipoClienteRepo tipo_cliente_repo;

    @Autowired
    private ITipoProveedorRepo tipo_proveedor_repo;

    @Autowired
    private EmpleadoService empleado_service;

    @Autowired
    private PersonaService persona_service;

    @Autowired
    private ClienteService cliente_service;

    @Autowired
    private ProveedorService proveedor_service;
    
    @Autowired
    private UserService usuario_service;

    @GetMapping("/inicio")
    public String inicio(){
        return "sistema/inicio.html";
    }

    @GetMapping("/lista-tipo-cliente")
    public String lista_tipo_cliente(Model model){
        List<TipoCliente> lista_tipo_cliente = tipo_cliente_repo.findAll();
        model.addAttribute("tipo_cliente", lista_tipo_cliente);
        return "sistema/clientes/lista_tipo_cliente";
    }

    @GetMapping("/ver-agregar-tipo-cliente")
    public String ver_agregar_tipo_cliente(Model model){
        TipoCliente tipo_cliente = new TipoCliente();
        model.addAttribute("tipo_cliente",tipo_cliente);
        return "sistema/clientes/nuevo_tipo_cliente";
    }

    @PostMapping("/agregar-tipo-cliente")
    public String agregar_tipo_cliente(@ModelAttribute("tipo_cliente") TipoCliente tipo_cliente,
        BindingResult bindingResult,
        RedirectAttributes redirectAttrs){
        if (bindingResult.hasErrors()) {
            redirectAttrs
                .addFlashAttribute("mensaje", "Error")
                .addFlashAttribute("clase", "warning");
            return "redirect:/sistema/agregar-tipo_cliente";
        }
        try {
            tipo_cliente_repo.save(tipo_cliente);
            redirectAttrs
            .addFlashAttribute("mensaje", "Agregado correctamente")
            .addFlashAttribute("clase", "success");

            return "redirect:/sistema/lista-tipo-cliente";
        } catch (Exception e) {
            redirectAttrs
                .addFlashAttribute("mensaje", "Error")
                .addFlashAttribute("clase", "warning");
            return "redirect:/sistema/lista-tipo-cliente";
            
        }
        
    }

    @GetMapping("/lista-cargos")
    public String lista_cargos(Model model){
        List<Cargos> lista_cargos = cargo_repo.findAll();
        model.addAttribute("cargos", lista_cargos);
        return "sistema/cargos/lista_cargos";
    }

    @GetMapping("/ver-agregar-cargo")
    public String ver_agregar_cargo(Model model){
        Cargos cargo = new Cargos();
        model.addAttribute("cargo",cargo);
        return "sistema/cargos/nuevo_cargo";
    }

    @PostMapping("/agregar-cargo")
    public String agregar_cargo(@ModelAttribute("cargo") Cargos cargo,
        BindingResult bindingResult,
        RedirectAttributes redirectAttrs){
        if (bindingResult.hasErrors()) {
            redirectAttrs
                .addFlashAttribute("mensaje", "Error")
                .addFlashAttribute("clase", "warning");
            return "redirect:/sistema/agregar-cargo";
        }

        cargo.setCreatedAt(fecha);
        cargo.setUpdatedAt(fecha);
            
        cargo_repo.save(cargo);

        redirectAttrs
            .addFlashAttribute("mensaje", "Agregado correctamente")
            .addFlashAttribute("clase", "success");

        
        return "redirect:/sistema/lista-cargos";
    }

    @GetMapping("/lista-empleados")
    public String lista_empleados(Model model){
        List<Empleado> lista_empleado = empleado_service.listaEmpleados();
        model.addAttribute("empleados", lista_empleado);
        return "sistema/empleados/lista_empleados";
    }

    @GetMapping("/ver-agregar-empleado")
    public String ver_agregar_empleado(Model model){
        Persona persona = new Persona();
        Empleado empleado = new Empleado();
        List<Cargos> lista_cargos = cargo_repo.findAll();
        model.addAttribute("persona",persona);
        model.addAttribute("empleado",empleado);
        model.addAttribute("lista_cargos",lista_cargos);
        return "sistema/empleados/nuevo_empleado";
    }

    @PostMapping("/agregar-empleado")
    public String agregar_empleado(@ModelAttribute("empleado") Empleado empleado,
        @ModelAttribute("persona") Persona persona,
        BindingResult bindingResult,
        RedirectAttributes redirectAttrs){
            if (bindingResult.hasErrors()) {
                redirectAttrs
                    .addFlashAttribute("mensaje", "Error")
                    .addFlashAttribute("clase", "warning");
                return "redirect:/sistema/agregar-empleado";
            }
            Boolean existe_persona = persona_service.findPersonaByRuc_Dni(persona.getRUCDNI());
            if (existe_persona == true) {
                redirectAttrs
                    .addFlashAttribute("mensaje", "Ese empleado ya se encuentra registrado")
                    .addFlashAttribute("clase", "warning");
                return "redirect:/sistema/agregar-empleado";
            }

            
        
            Usuario new_usuario = new Usuario();
            new_usuario.setUsuario(persona.getCorreo());
            new_usuario.setPassword(persona.getCorreo());
            persona.setCreatedAt(fecha);
            persona.setUpdatedAt(fecha);
            persona.setEstado(1);
            persona_service.guardar(persona);

            Persona find_last_persona = persona_service.findLastIdPersona(persona.getRUCDNI());
            if (find_last_persona != null) {
                empleado.setPersonaId(find_last_persona);
                empleado.setCreatedAt(fecha);
                empleado.setUpdatedAt(fecha);
                
                empleado_service.guardar(empleado);
                usuario_service.guardar(new_usuario);

                redirectAttrs
                    .addFlashAttribute("mensaje", "Agregado correctamente")
                    .addFlashAttribute("clase", "success");
            }else{
                redirectAttrs
                    .addFlashAttribute("mensaje", "Error al agregar el empleado")
                    .addFlashAttribute("clase", "warning");
            }
        
            return "redirect:/sistema/lista-empleados";
    }

    @GetMapping("/lista-clientes")
    public String lista_cliente(Model model){
        List<Cliente> lista_cliente = cliente_service.listaClientes();
        model.addAttribute("cliente", lista_cliente);
        return "sistema/clientes/lista_clientes";
    }

    @GetMapping("/ver-agregar-cliente")
    public String ver_agregar_cliente(Model model){
        Persona persona = new Persona();
        Cliente cliente = new Cliente();
        List<TipoCliente> lista_tipo_cliente = tipo_cliente_repo.findAll();
        model.addAttribute("persona", persona);
        model.addAttribute("cliente", cliente);
        model.addAttribute("tipo_cliente", lista_tipo_cliente);
        return "sistema/clientes/nuevo_cliente";
    }

    @PostMapping("/agregar-cliente")
    public String agregar_cliente(@ModelAttribute("cliente") Cliente cliente,
        @ModelAttribute("persona") Persona persona,
        BindingResult bindingResult,
        RedirectAttributes redirectAttrs){
        if (bindingResult.hasErrors()) {
            redirectAttrs
                .addFlashAttribute("mensaje", "Error")
                .addFlashAttribute("clase", "warning");
            return "redirect:/sistema/agregar-clientes";
        }
        try {
            Boolean existe_persona = persona_service.findPersonaByRuc_Dni(persona.getRUCDNI());
            if (existe_persona == true) {
                redirectAttrs
                    .addFlashAttribute("mensaje", "Ese cliente ya se encuentra registrado")
                    .addFlashAttribute("clase", "warning");
                return "redirect:/sistema/agregar-clientes";
            }

            persona.setCreatedAt(fecha);
            persona.setUpdatedAt(fecha);
            persona.setEstado(1);
            persona_service.guardar(persona);

            Persona find_last_persona = persona_service.findLastIdPersona(persona.getRUCDNI());
            if (find_last_persona != null) {
                cliente.setCreatedAt(fecha);
                cliente.setUpdatedAt(fecha);
                cliente_service.guardar(cliente);
                //FALTA OBTENER LA SESSION DEL EMPLEADO
                redirectAttrs
                    .addFlashAttribute("mensaje", "Agregado correctamente")
                    .addFlashAttribute("clase", "success");
                return "redirect:/sistema/lista-clientes";
            }else{
                redirectAttrs
                    .addFlashAttribute("mensaje", "Error al agregar el cliente")
                    .addFlashAttribute("clase", "warning");
                return "redirect:/sistema/lista-clientes";
            }

        } catch (Exception e) {
            redirectAttrs
                .addFlashAttribute("mensaje", "Error")
                .addFlashAttribute("clase", "warning");
            return "redirect:/sistema/lista-clientes";
            
        }
        
    }

    @GetMapping("/lista-tipo-proveedor")
    public String lista_tipo_proveedor(Model model){
        List<TipoProveedor> lista_tipo_proveedor = tipo_proveedor_repo.findAll();
        model.addAttribute("tipo_proveedor", lista_tipo_proveedor);
        return "sistema/proveedores/lista_tipo_proveedor";
    }

    @GetMapping("/ver-agregar-tipo-proveedor")
    public String ver_agregar_tipo_proveedor(Model model){
        TipoProveedor tipo_proveedor = new TipoProveedor();
        model.addAttribute("tipo_proveedor",tipo_proveedor);
        return "sistema/proveedores/nuevo_tipo_proveedor";
    }

    @PostMapping("/agregar-tipo-proveedor")
    public String agregar_tipo_proveedor(@ModelAttribute("tipo_proveedor") TipoProveedor tipo_proveedor,
        BindingResult bindingResult,
        RedirectAttributes redirectAttrs){
        if (bindingResult.hasErrors()) {
            redirectAttrs
                .addFlashAttribute("mensaje", "Error")
                .addFlashAttribute("clase", "warning");
            return "redirect:/sistema/agregar-tipo_proveedor";
        }
        try {
            tipo_proveedor_repo.save(tipo_proveedor);
            redirectAttrs
            .addFlashAttribute("mensaje", "Agregado correctamente")
            .addFlashAttribute("clase", "success");

            return "redirect:/sistema/lista-tipo-proveedor";
        } catch (Exception e) {
            redirectAttrs
                .addFlashAttribute("mensaje", "Error")
                .addFlashAttribute("clase", "warning");
            return "redirect:/sistema/lista-tipo-proveedor";
            
        }
        
    }

    @GetMapping("/lista-proveedores")
    public String proveedores(Model model){
        List<Proveedor> lista_proveedor = proveedor_service.listaProveedores();
        model.addAttribute("proveedor", lista_proveedor);
        return "sistema/proveedores/lista_proveedores";
    }

    @GetMapping("/ver-agregar-proveedor")
    public String ver_agregar_proveedor(Model model){
        Persona persona = new Persona();
        Proveedor proveedor = new Proveedor();
        List<TipoProveedor> lista_tipo_proveedor = tipo_proveedor_repo.findAll();
        model.addAttribute("persona", persona);
        model.addAttribute("proveedor", proveedor);
        model.addAttribute("tipo_proveedor", lista_tipo_proveedor);
        return "sistema/proveedores/nuevo_proveedor";
    }

    @PostMapping("/agregar-proveedor")
    public String agregar_proveedor(@ModelAttribute("proveedor") Proveedor proveedor,
        @ModelAttribute("persona") Persona persona,
        BindingResult bindingResult,
        RedirectAttributes redirectAttrs){
        if (bindingResult.hasErrors()) {
            redirectAttrs
                .addFlashAttribute("mensaje", "Error")
                .addFlashAttribute("clase", "warning");
            return "redirect:/sistema/agregar-proveedor";
        }
        try {
            Boolean existe_persona = persona_service.findPersonaByRuc_Dni(persona.getRUCDNI());
            if (existe_persona == true) {
                redirectAttrs
                    .addFlashAttribute("mensaje", "Ese proveedor ya se encuentra registrado")
                    .addFlashAttribute("clase", "warning");
                return "redirect:/sistema/agregar-proveedor";
            }

            persona.setCreatedAt(fecha);
            persona.setUpdatedAt(fecha);
            persona.setEstado(1);
            persona_service.guardar(persona);

            Persona find_last_persona = persona_service.findLastIdPersona(persona.getRUCDNI());
            if (find_last_persona != null) {
                proveedor.setCreatedAt(fecha);
                proveedor.setUpdatedAt(fecha);
                proveedor_service.guardar(proveedor);
                //FALTA OBTENER LA SESSION DEL EMPLEADO
                redirectAttrs
                    .addFlashAttribute("mensaje", "Agregado correctamente")
                    .addFlashAttribute("clase", "success");
                return "redirect:/sistema/lista-proveedores";
            }else{
                redirectAttrs
                    .addFlashAttribute("mensaje", "Error al agregar el proveedor")
                    .addFlashAttribute("clase", "warning");
                return "redirect:/sistema/lista-proveedores";
            }

        } catch (Exception e) {
            redirectAttrs
                .addFlashAttribute("mensaje", "Error")
                .addFlashAttribute("clase", "warning");
            return "redirect:/sistema/lista-proveedores";
            
        }
        
    }
    
}
