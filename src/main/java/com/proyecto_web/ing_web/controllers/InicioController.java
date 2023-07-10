package com.proyecto_web.ing_web.controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.proyecto_web.ing_web.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto_web.ing_web.entities.Almacen;
import com.proyecto_web.ing_web.entities.Cargos;
import com.proyecto_web.ing_web.entities.Categoria;
import com.proyecto_web.ing_web.entities.Cliente;
import com.proyecto_web.ing_web.entities.Conductor;
import com.proyecto_web.ing_web.entities.Empleado;
import com.proyecto_web.ing_web.entities.Envio;
import com.proyecto_web.ing_web.entities.Persona;
import com.proyecto_web.ing_web.entities.Producto;
import com.proyecto_web.ing_web.entities.Proveedor;
import com.proyecto_web.ing_web.entities.Ruta;
import com.proyecto_web.ing_web.entities.TipoCliente;
import com.proyecto_web.ing_web.entities.TipoProveedor;
import com.proyecto_web.ing_web.entities.Unidad;
import com.proyecto_web.ing_web.entities.Usuario;
import com.proyecto_web.ing_web.entities.Factura;
import com.proyecto_web.ing_web.entities.Ingreso;
import com.proyecto_web.ing_web.entities.Vehiculo;
import com.proyecto_web.ing_web.entities.almacen_productos;
import com.proyecto_web.ing_web.entities.envio_productos;
import com.proyecto_web.ing_web.entities.producto_unidades;
import com.proyecto_web.ing_web.implementacion.EmpleadoServiceImpl;
import com.proyecto_web.ing_web.servicios.ClienteService;
import com.proyecto_web.ing_web.servicios.ConductorService;
import com.proyecto_web.ing_web.servicios.EmpleadoService;
import com.proyecto_web.ing_web.servicios.PersonaService;
import com.proyecto_web.ing_web.servicios.ProveedorService;
import com.proyecto_web.ing_web.servicios.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/sistema")
public class InicioController {

    private LocalDate localDate = LocalDate.now();

    @Autowired
    private ICargoRepo cargo_repo;

    @Autowired
    private ITipoClienteRepo tipo_cliente_repo;

    @Autowired
    private ITipoProveedorRepo tipo_proveedor_repo;

    @Autowired
    private EmpleadoService empleado_service;

    @Autowired
    private IEmpleadoRepo empleado_dao;

    @Autowired
    private PersonaService persona_service;

    @Autowired
    private ClienteService cliente_service;

    @Autowired
    private ProveedorService proveedor_service;

    @Autowired
    private IAlmacenRepo almacen_repo;

    @Autowired
    private IProductoRepo producto_repo;

    @Autowired
    private IClienteRepo cliente_repo;

    @Autowired
    private IRutaRepo ruta_repo;

    @Autowired
    private ICategoriaRepo categoria_repo;

    @Autowired
    private IVehiculoRepo vehiculo_repo;

    @Autowired
    private IUnidadRepo unidad_repo;

    @Autowired
    private IConductorRepo conductor_repo;

    @Autowired
    private IFacturaRepo factura_repo;

    @Autowired
    private ConductorService conducto_service;

    @Autowired
    private UserService usuario_service;

    @Autowired
    private IEnvioRepo envio_repo;

    @Autowired
    private IIngresoRepo ingreso_repo;

    @Autowired
    private IEnvio_productosRepo envio_prod_repo;

    @Autowired
    private IProveedorRepo proveedor_repo;

    @Autowired
    private IAlmacen_producto almacen_prod_repo;

    @Autowired
    private IProducto_unidad producto_unidad_repo;

    @GetMapping("/inicio")
    public String inicio(Model model, HttpSession session) {
        Usuario usu_logueado = (Usuario) session.getAttribute("usuariosession");
        model.addAttribute("usuario_logeado", usu_logueado);
        return "sistema/inicio.html";
    }

    //----------TIPO CLIENTE -------------------------------------

    @GetMapping("/lista-tipo-cliente")
    public String lista_tipo_cliente(Model model) {
        List<TipoCliente> lista_tipo_cliente = tipo_cliente_repo.findAll();
        model.addAttribute("tipo_cliente", lista_tipo_cliente);
        return "sistema/clientes/lista_tipo_cliente";
    }

    @GetMapping("/ver-agregar-tipo-cliente")
    public String ver_agregar_tipo_cliente(Model model) {
        TipoCliente tipo_cliente = new TipoCliente();
        model.addAttribute("tipo_cliente", tipo_cliente);
        return "sistema/clientes/nuevo_tipo_cliente";
    }

    @PostMapping("/agregar-tipo-cliente")
    public String agregar_tipo_cliente(@ModelAttribute("tipo_cliente") TipoCliente tipo_cliente,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttrs) {
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

    @GetMapping("/ver-editar-tipo-cliente/{id_tipo_cliente}")
    public String editarTipoCliente(@PathVariable Integer id_tipo_cliente, Model model) {
        Optional<TipoCliente> tipo_clienteFind = tipo_cliente_repo.findById(id_tipo_cliente);
        model.addAttribute("tipo_cliente", tipo_clienteFind.get());
        return "sistema/clientes/editar_tipo_cliente";
    }

    @PostMapping("/validar-edicion-tipo-cliente/{id_tipo_cliente}")
    public String saveEdicionTipoCliente(
            @RequestParam(value = "txtid_tipo_cliente") Integer id_tipo_cliente,
            @ModelAttribute("tipo_cliente") @Validated TipoCliente tipo_cliente,
            BindingResult bindingResult,
            RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            if (tipo_cliente.getId_tipo_cliente() != null) {
                redirectAttrs
                        .addFlashAttribute("mensaje", "Error")
                        .addFlashAttribute("clase", "warning");
                return "redirect:/sistema/ver-editar-tipo-cliente/" + id_tipo_cliente;
            }
            redirectAttrs
                    .addFlashAttribute("mensaje", "Error")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/sistema/ver-editar-tipo-cliente/" + id_tipo_cliente;
        }
        Optional<TipoCliente> tipo_clienteFind = tipo_cliente_repo.findById(id_tipo_cliente);
        tipo_clienteFind.get().setDescripcion(tipo_cliente.getDescripcion());
        tipo_cliente_repo.save(tipo_clienteFind.get());
        redirectAttrs
                .addFlashAttribute("mensaje", "Editado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/sistema/lista-tipo-cliente";
    }

    @PostMapping("/eliminar-tipo-cliente")
    public String eliminarTipoCliente(RedirectAttributes redirectAttrs,
                                      @RequestParam(value = "txtid_tipo_cliente") Integer idtipocliente) {
        redirectAttrs
                .addFlashAttribute("mensaje", "Eliminado correctamente")
                .addFlashAttribute("clase", "warning");
        Optional<TipoCliente> tipo_clienteFind = tipo_cliente_repo.findById(idtipocliente);
        tipo_cliente_repo.delete(tipo_clienteFind.get());
        return "redirect:/sistema/lista-tipo-cliente";
    }

    //--------------------------------------------------------------------------------

    //-------------------------------CARGOS-------------------------------------------------

    @GetMapping("/lista-cargos")
    public String lista_cargos(Model model) {
        List<Cargos> lista_cargos = cargo_repo.findAll();
        model.addAttribute("cargos", lista_cargos);
        return "sistema/cargos/lista_cargos";
    }

    @GetMapping("/ver-agregar-cargo")
    public String ver_agregar_cargo(Model model) {
        Cargos cargo = new Cargos();
        model.addAttribute("cargo", cargo);
        return "sistema/cargos/nuevo_cargo";
    }

    @PostMapping("/agregar-cargo")
    public String agregar_cargo(@ModelAttribute("cargo") Cargos cargo,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Error")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/sistema/agregar-cargo";
        }

        cargo.setCreatedAt(localDate);
        cargo.setUpdatedAt(localDate);

        cargo_repo.save(cargo);

        redirectAttrs
                .addFlashAttribute("mensaje", "Agregado correctamente")
                .addFlashAttribute("clase", "success");


        return "redirect:/sistema/lista-cargos";
    }

    @GetMapping("/ver-editar-cargo/{id_cargo}")
    public String editarCargo(@PathVariable Integer id_cargo, Model model) {
        Optional<Cargos> cargoFind = cargo_repo.findById(id_cargo);
        model.addAttribute("cargo", cargoFind.get());
        return "sistema/cargos/editar_cargo";
    }

    @PostMapping("/validar-edicion-cargo/{id_cargo}")
    public String saveEdicionCargo(
            @RequestParam(value = "txtid_cargo") Integer id_cargo,
            @ModelAttribute("cargo") @Validated Cargos cargo,
            BindingResult bindingResult,
            RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            if (cargo.getId() != null) {
                redirectAttrs
                        .addFlashAttribute("mensaje", "Error")
                        .addFlashAttribute("clase", "warning");
                return "redirect:/sistema/ver-editar-cargo/" + id_cargo;
            }
            redirectAttrs
                    .addFlashAttribute("mensaje", "Error")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/sistema/ver-editar-cargo/" + id_cargo;
        }
        Optional<Cargos> cargoFind = cargo_repo.findById(id_cargo);
        cargoFind.get().setDescripcion(cargo.getDescripcion());
        cargo_repo.save(cargoFind.get());
        redirectAttrs
                .addFlashAttribute("mensaje", "Editado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/sistema/lista-cargos";
    }

    @PostMapping("/eliminar-cargo")
    public String eliminarCargo(RedirectAttributes redirectAttrs,
                                @RequestParam(value = "txtid_cargo") Integer idcargo) {
        redirectAttrs
                .addFlashAttribute("mensaje", "Eliminado correctamente")
                .addFlashAttribute("clase", "warning");
        Optional<Cargos> cargoFind = cargo_repo.findById(idcargo);
        cargo_repo.delete(cargoFind.get());
        return "redirect:/sistema/lista-cargos";
    }

    //--------------------------------------------------------------------------------

    @GetMapping("/lista-empleados")
    public String lista_empleados(Model model) {
        List<Empleado> lista_empleado = empleado_service.listaEmpleados();
        model.addAttribute("empleados", lista_empleado);
        return "sistema/empleados/lista_empleados";
    }

    @GetMapping("/ver-agregar-empleado")
    public String ver_agregar_empleado(Model model) {
        Persona persona = new Persona();
        Empleado empleado = new Empleado();
        List<Cargos> lista_cargos = cargo_repo.findAll();
        model.addAttribute("persona", persona);
        model.addAttribute("empleado", empleado);
        model.addAttribute("lista_cargos", lista_cargos);
        return "sistema/empleados/nuevo_empleado";
    }

    @PostMapping("/agregar-empleado")
    public String agregar_empleado(@ModelAttribute("empleado") Empleado empleado,
                                   @ModelAttribute("persona") Persona persona,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttrs) {
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
        persona.setCreatedAt(localDate);
        persona.setUpdatedAt(localDate);
        persona.setEstado(1);
        persona_service.guardar(persona);

        Persona find_last_persona = persona_service.findLastIdPersona(persona.getRUCDNI());
        if (find_last_persona != null) {
            empleado.setPersonaId(find_last_persona);
            empleado.setCreatedAt(localDate);
            empleado.setUpdatedAt(localDate);

            empleado_service.guardar(empleado);
            usuario_service.guardar(new_usuario);

            redirectAttrs
                    .addFlashAttribute("mensaje", "Agregado correctamente")
                    .addFlashAttribute("clase", "success");
        } else {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Error al agregar el empleado")
                    .addFlashAttribute("clase", "warning");
        }

        return "redirect:/sistema/lista-empleados";
    }

    @GetMapping("/lista-clientes")
    public String lista_cliente(Model model) {
        List<Cliente> lista_cliente = cliente_service.listaClientes();
        model.addAttribute("cliente", lista_cliente);
        return "sistema/clientes/lista_clientes";
    }

    @GetMapping("/ver-agregar-cliente")
    public String ver_agregar_cliente(Model model) {
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
                                  RedirectAttributes redirectAttrs,
                                  HttpSession session) {
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

            persona.setCreatedAt(localDate);
            persona.setUpdatedAt(localDate);
            persona.setEstado(1);
            persona_service.guardar(persona);

            Persona find_last_persona = persona_service.findLastIdPersona(persona.getRUCDNI());
            Usuario usu_logueado = (Usuario) session.getAttribute("usuariosession");
            Empleado find_empleado = empleado_dao.findByCorreo(usu_logueado.getUsuario());
            if (find_last_persona != null) {
                cliente.setEmpleadoId(find_empleado);
                cliente.setPersonaId(find_last_persona);
                cliente.setCreatedAt(localDate);
                cliente.setUpdatedAt(localDate);
                cliente_service.guardar(cliente);
                redirectAttrs
                        .addFlashAttribute("mensaje", "Agregado correctamente")
                        .addFlashAttribute("clase", "success");
                return "redirect:/sistema/lista-clientes";
            } else {
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

    //--------------------TIPO PROVEEDOR----------------------------------

    @GetMapping("/lista-tipo-proveedor")
    public String lista_tipo_proveedor(Model model) {
        List<TipoProveedor> lista_tipo_proveedor = tipo_proveedor_repo.findAll();
        model.addAttribute("tipo_proveedor", lista_tipo_proveedor);
        return "sistema/proveedores/lista_tipo_proveedor";
    }

    @GetMapping("/ver-agregar-tipo-proveedor")
    public String ver_agregar_tipo_proveedor(Model model) {
        TipoProveedor tipo_proveedor = new TipoProveedor();
        model.addAttribute("tipo_proveedor", tipo_proveedor);
        return "sistema/proveedores/nuevo_tipo_proveedor";
    }

    @PostMapping("/agregar-tipo-proveedor")
    public String agregar_tipo_proveedor(@ModelAttribute("tipo_proveedor") TipoProveedor tipo_proveedor,
                                         BindingResult bindingResult,
                                         RedirectAttributes redirectAttrs) {
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

    @GetMapping("/ver-editar-tipo-proveedor/{id_tipo_proveedor}")
    public String editarTipoPorveedor(@PathVariable Integer id_tipo_proveedor, Model model) {
        Optional<TipoProveedor> tipo_proveedorFind = tipo_proveedor_repo.findById(id_tipo_proveedor);
        model.addAttribute("tipo_proveedor", tipo_proveedorFind.get());
        return "sistema/proveedores/editar_tipo_proveedor";
    }

    @PostMapping("/validar-edicion-tipo-proveedor/{id_tipo_proveedor}")
    public String saveEdicionTipoProveedor(
            @RequestParam(value = "txtid_tipo_proveedor") Integer id_tipo_proveedor,
            @ModelAttribute("tipo_proveedor") @Validated TipoProveedor tipo_proveedor,
            BindingResult bindingResult,
            RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            if (tipo_proveedor.getId_tipo_proveedor() != null) {
                redirectAttrs
                        .addFlashAttribute("mensaje", "Error")
                        .addFlashAttribute("clase", "warning");
                return "redirect:/sistema/ver-editar-tipo-proveedor/" + id_tipo_proveedor;
            }
            redirectAttrs
                    .addFlashAttribute("mensaje", "Error")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/sistema/ver-editar-tipo-proveedor/" + id_tipo_proveedor;
        }
        Optional<TipoProveedor> tipo_proveedorFind = tipo_proveedor_repo.findById(id_tipo_proveedor);
        tipo_proveedorFind.get().setDescripcion(tipo_proveedor.getDescripcion());
        tipo_proveedor_repo.save(tipo_proveedorFind.get());
        redirectAttrs
                .addFlashAttribute("mensaje", "Editado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/sistema/lista-tipo-proveedor";
    }

    @PostMapping("/eliminar-tipo-proveedor")
    public String eliminarTipoProveedor(RedirectAttributes redirectAttrs,
                                        @RequestParam(value = "txtid_tipo_proveedor") Integer idtipoproveedor) {
        redirectAttrs
                .addFlashAttribute("mensaje", "Eliminado correctamente")
                .addFlashAttribute("clase", "warning");
        Optional<TipoProveedor> tipo_proveedorFind = tipo_proveedor_repo.findById(idtipoproveedor);
        tipo_proveedor_repo.delete(tipo_proveedorFind.get());
        return "redirect:/sistema/lista-tipo-proveedor";
    }

    //-------------------------------------------------------------------------

    @GetMapping("/lista-proveedores")
    public String proveedores(Model model) {
        List<Proveedor> lista_proveedor = proveedor_service.listaProveedores();
        model.addAttribute("proveedor", lista_proveedor);
        return "sistema/proveedores/lista_proveedores";
    }

    @GetMapping("/ver-agregar-proveedor")
    public String ver_agregar_proveedor(Model model) {
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
                                    RedirectAttributes redirectAttrs,
                                    HttpSession session) {
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

            persona.setCreatedAt(localDate);
            persona.setUpdatedAt(localDate);
            persona.setEstado(1);
            persona_service.guardar(persona);

            Persona find_last_persona = persona_service.findLastIdPersona(persona.getRUCDNI());
            Usuario usu_logueado = (Usuario) session.getAttribute("usuariosession");
            Empleado find_empleado = empleado_dao.findByCorreo(usu_logueado.getUsuario());
            if (find_last_persona != null) {
                proveedor.setEmpleadoId(find_empleado);
                proveedor.setPersonaId(find_last_persona);
                proveedor.setCreatedAt(localDate);
                proveedor.setUpdatedAt(localDate);
                proveedor_service.guardar(proveedor);
                redirectAttrs
                        .addFlashAttribute("mensaje", "Agregado correctamente")
                        .addFlashAttribute("clase", "success");
                return "redirect:/sistema/lista-proveedores";
            } else {
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

    //--------------------------------ALMACEN------------------------------------

    @GetMapping("/lista-almacen")
    public String lista_almacen(Model model) {
        List<Almacen> lista_almacen = almacen_repo.findAll();
        model.addAttribute("almacen", lista_almacen);
        return "sistema/almacen/lista_almacen";
    }

    @GetMapping("/ver-agregar-almacen")
    public String ver_agregar_almacen(Model model) {
        Almacen almacen = new Almacen();
        model.addAttribute("almacen", almacen);
        return "sistema/almacen/nuevo_almacen";
    }

    @PostMapping("/agregar-almacen")
    public String agregar_almacen(@ModelAttribute("almacen") Almacen almacen,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttrs,
                                  HttpSession session) {
        if (bindingResult.hasErrors()) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Error binding")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/sistema/agregar-almacen";
        }
        try {
            Usuario usu_logueado = (Usuario) session.getAttribute("usuariosession");

            System.out.println("User logged: " + usu_logueado);
            Empleado find_empleado = empleado_dao.findByCorreo(usu_logueado.getUsuario());
            System.out.print("Inside try: " + find_empleado.getPersonaId().getCorreo());
            almacen.setEstado(true);
            almacen.setCreatedAt(localDate);
            almacen.setUpdatedAt(localDate);
            almacen.setEmpleadoId(find_empleado);
            almacen_repo.save(almacen);
            redirectAttrs
                    .addFlashAttribute("mensaje", "Agregado correctamente")
                    .addFlashAttribute("clase", "success");

            return "redirect:/sistema/lista-almacen";
        } catch (Exception e) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Error exception")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/sistema/lista-almacen";

        }

    }

    @GetMapping("/ver-editar-almacen/{id_almacen}")
    public String editarAlmacen(@PathVariable Integer id_almacen, Model model) {
        Optional<Almacen> almacenFind = almacen_repo.findById(id_almacen);
        model.addAttribute("almacen", almacenFind.get());
        return "sistema/almacen/editar_almacen";
    }

    @PostMapping("/validar-edicion-almacen/{id_almacen}")
    public String saveEdicionAlmacen(
            @RequestParam(value = "txtid_almacen") Integer id_almacen,
            @ModelAttribute("almacen") @Validated Almacen almacen,
            BindingResult bindingResult,
            RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            if (almacen.getId() != null) {
                redirectAttrs
                        .addFlashAttribute("mensaje", "Error")
                        .addFlashAttribute("clase", "warning");
                return "redirect:/sistema/ver-editar-almacen/" + id_almacen;
            }
            redirectAttrs
                    .addFlashAttribute("mensaje", "Error")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/sistema/ver-editar-almacen/" + id_almacen;
        }
        Optional<Almacen> almacenFind = almacen_repo.findById(id_almacen);
        almacenFind.get().setNombre(almacen.getNombre());
        almacenFind.get().setDireccion(almacen.getDireccion());
        almacenFind.get().setCapacidad(almacen.getCapacidad());
        almacenFind.get().setCiudad(almacen.getCiudad());
        almacenFind.get().setPais(almacen.getPais());
        almacen_repo.save(almacenFind.get());
        redirectAttrs
                .addFlashAttribute("mensaje", "Editado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/sistema/lista-almacen";
    }

    @PostMapping("/eliminar-almacen")
    public String eliminarAlmacen(RedirectAttributes redirectAttrs,
                                  @RequestParam(value = "txtid_almacen") Integer idalmacen) {
        redirectAttrs
                .addFlashAttribute("mensaje", "Eliminado correctamente")
                .addFlashAttribute("clase", "warning");
        Optional<Almacen> almacenFind = almacen_repo.findById(idalmacen);
        almacenFind.get().setEstado(false);
        almacen_repo.save(almacenFind.get());
        return "redirect:/sistema/lista-almacen";
    }

    //--------------------------------------------------------------------

    @GetMapping("/lista-conductor")
    public String lista_conductor(Model model) {
        List<Conductor> lista_conductor = conducto_service.listaConductores();
        model.addAttribute("conductor", lista_conductor);
        return "sistema/conductores/lista_conductores";
    }

    @GetMapping("/ver-agregar-conductor")
    public String ver_agregar_conductor(Model model) {
        Conductor conductor = new Conductor();
        Persona persona = new Persona();
        model.addAttribute("persona", persona);
        model.addAttribute("conductor", conductor);
        return "sistema/conductores/nuevo_conductor";
    }

    @PostMapping("/agregar-conductor")
    public String agregar_conductor(@ModelAttribute("conductor") Conductor conductor,
                                    @ModelAttribute("persona") Persona persona,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttrs,
                                    HttpSession session) {
        System.out.println(bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Error")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/sistema/agregar-conductor";
        }
        System.out.println("After hasErrors: " + persona.getRUCDNI());
        try {
            Boolean existe_persona = persona_service.findPersonaByRuc_Dni(persona.getRUCDNI());
            System.out.println("Before conditional : " + existe_persona);
            if (existe_persona) {
                redirectAttrs
                        .addFlashAttribute("mensaje", "Ese conductor ya se encuentra registrado")
                        .addFlashAttribute("clase", "warning");
                return "redirect:/sistema/lista-conductor";
            }
            System.out.println("First conditional passed");

            persona.setCreatedAt(localDate);
            persona.setUpdatedAt(localDate);
            persona.setEstado(1);
            persona_service.guardar(persona);

            Persona find_last_persona = persona_service.findLastIdPersona(persona.getRUCDNI());
            Usuario usu_logueado = (Usuario) session.getAttribute("usuariosession");


            if (find_last_persona != null) {
                conductor.setCreatedAt(localDate);
                conductor.setUpdatedAt(localDate);
                if (!(usu_logueado.getUsuario() == "admin@gmail.com")) {
                    Empleado find_empleado = empleado_dao.findByCorreo(usu_logueado.getUsuario());
                    conductor.setEmpleadoId(find_empleado);
                }else{
                    conductor.setEmpleadoId(null);
                }

                conductor.setPersonaId(find_last_persona);
                conductor_repo.save(conductor);
                redirectAttrs
                        .addFlashAttribute("mensaje", "Agregado correctamente")
                        .addFlashAttribute("clase", "success");

                return "redirect:/sistema/lista-conductor";
            } else {
                redirectAttrs
                        .addFlashAttribute("mensaje", "Error al agregar el conductor")
                        .addFlashAttribute("clase", "warning");
                return "redirect:/sistema/lista-conductor";
            }

        } catch (Exception e) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Error")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/sistema/lista-conductor";

        }

    }

    //----------------------VEHICULO------------------------------------------

    @GetMapping("/lista-vehiculo")
    public String lista_vehiculo(Model model) {
        List<Vehiculo> lista_vehiculo = vehiculo_repo.findAll();
        model.addAttribute("vehiculo", lista_vehiculo);
        return "sistema/vehiculos/lista_vehiculos";
    }

    @GetMapping("/ver-agregar-vehiculo")
    public String ver_agregar_vehiculo(Model model) {
        Vehiculo vehiculo = new Vehiculo();
        model.addAttribute("vehiculo", vehiculo);
        return "sistema/vehiculos/nuevo_vehiculo";
    }

    @PostMapping("/agregar-vehiculo")
    public String agregar_vehiculo(@ModelAttribute("vehiculo") Vehiculo vehiculo,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttrs,
                                   HttpSession session) {
        if (bindingResult.hasErrors()) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Error")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/sistema/agregar-vehiculo";
        }
        try {
            Usuario usu_logueado = (Usuario) session.getAttribute("usuariosession");
            Empleado find_empleado = empleado_dao.findByCorreo(usu_logueado.getUsuario());
            vehiculo.setCreatedAt(localDate);
            vehiculo.setUpdatedAt(localDate);
            vehiculo.setEmpleadoId(find_empleado);
            vehiculo_repo.save(vehiculo);
            redirectAttrs
                    .addFlashAttribute("mensaje", "Agregado correctamente")
                    .addFlashAttribute("clase", "success");

            return "redirect:/sistema/lista-vehiculo";
        } catch (Exception e) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Error")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/sistema/lista-vehiculo";

        }

    }

    @GetMapping("/ver-editar-vehiculo/{id_vehiculo}")
    public String editarVehiculo(@PathVariable Integer id_vehiculo, Model model) {
        Optional<Vehiculo> vehiculoFind = vehiculo_repo.findById(id_vehiculo);
        model.addAttribute("vehiculo", vehiculoFind.get());
        return "sistema/vehiculos/editar_vehiculo";
    }

    @PostMapping("/validar-edicion-vehiculo/{id_vehiculo}")
    public String saveEdicionVehiculo(
            @RequestParam(value = "txtid_vehiculo") Integer id_vehiculo,
            @ModelAttribute("vehiculo") @Validated Vehiculo vehiculo,
            BindingResult bindingResult,
            RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            if (vehiculo.getId() != null) {
                redirectAttrs
                        .addFlashAttribute("mensaje", "Error")
                        .addFlashAttribute("clase", "warning");
                return "redirect:/sistema/ver-editar-vehiculo/" + id_vehiculo;
            }
            redirectAttrs
                    .addFlashAttribute("mensaje", "Error")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/sistema/ver-editar-vehiculo/" + id_vehiculo;
        }
        Optional<Vehiculo> vehiculoFind = vehiculo_repo.findById(id_vehiculo);
        vehiculoFind.get().setPlaca(vehiculo.getPlaca());
        vehiculoFind.get().setMarca(vehiculo.getMarca());
        vehiculoFind.get().setModelo(vehiculo.getModelo());
        vehiculoFind.get().setYear(vehiculo.getYear());
        vehiculoFind.get().setUnidad(vehiculo.getUnidad());
        vehiculoFind.get().setCapacidad_carga(vehiculo.getCapacidad_carga());
        vehiculo_repo.save(vehiculoFind.get());
        redirectAttrs
                .addFlashAttribute("mensaje", "Editado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/sistema/lista-vehiculo";
    }

    @PostMapping("/eliminar-vehiculo")
    public String eliminarVehiculo(RedirectAttributes redirectAttrs,
                                   @RequestParam(value = "txtid_vehiculo") Integer idvehiculo) {
        redirectAttrs
                .addFlashAttribute("mensaje", "Eliminado correctamente")
                .addFlashAttribute("clase", "warning");
        Optional<Vehiculo> vehiculoFind = vehiculo_repo.findById(idvehiculo);
        vehiculo_repo.delete(vehiculoFind.get());
        return "redirect:/sistema/lista-vehiculo";
    }
    //----------------------------------------------------------------------

    //----------------------UNIDAD------------------------------------------

    @GetMapping("/lista-unidad")
    public String lista_unidad(Model model) {
        List<Unidad> lista_unidad = unidad_repo.findAll();
        model.addAttribute("unidad", lista_unidad);
        return "sistema/unidades/lista_unidad";
    }

    @GetMapping("/ver-agregar-unidad")
    public String ver_agregar_unidad(Model model) {
        Unidad unidad = new Unidad();
        model.addAttribute("unidad", unidad);
        return "sistema/unidades/nueva_unidad";
    }

    @PostMapping("/agregar-unidad")
    public String agregar_unidad(@ModelAttribute("unidad") Unidad unidad,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttrs,
                                 HttpSession session) {
        if (bindingResult.hasErrors()) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Error")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/sistema/agregar-unidad";
        }
        try {
            Usuario usu_logueado = (Usuario) session.getAttribute("usuariosession");
            Empleado find_empleado = empleado_dao.findByCorreo(usu_logueado.getUsuario());
            unidad.setCreatedAt(localDate);
            unidad.setUpdatedAt(localDate);
            unidad.setEmpleadoId(find_empleado);
            unidad_repo.save(unidad);
            redirectAttrs
                    .addFlashAttribute("mensaje", "Agregado correctamente")
                    .addFlashAttribute("clase", "success");

            return "redirect:/sistema/lista-unidad";
        } catch (Exception e) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Error")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/sistema/lista-unidad";

        }

    }

    @GetMapping("/ver-editar-unidad/{id_unidad}")
    public String editarUnidad(@PathVariable Integer id_unidad, Model model) {
        Optional<Unidad> unidadFind = unidad_repo.findById(id_unidad);
        model.addAttribute("unidad", unidadFind.get());
        return "sistema/unidades/editar_unidad";
    }

    @PostMapping("/validar-edicion-unidad/{id_unidad}")
    public String saveEdicionUnidad(
            @RequestParam(value = "txtid_unidad") Integer id_unidad,
            @ModelAttribute("unidad") @Validated Unidad unidad,
            BindingResult bindingResult,
            RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            if (unidad.getId() != null) {
                redirectAttrs
                        .addFlashAttribute("mensaje", "Error")
                        .addFlashAttribute("clase", "warning");
                return "redirect:/sistema/ver-editar-unidad/" + id_unidad;
            }
            redirectAttrs
                    .addFlashAttribute("mensaje", "Error")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/sistema/ver-editar-unidad/" + id_unidad;
        }
        Optional<Unidad> unidadFind = unidad_repo.findById(id_unidad);
        unidadFind.get().setNombre(unidad.getNombre());
        unidadFind.get().setDescripcion(unidad.getDescripcion());
        unidad_repo.save(unidadFind.get());
        redirectAttrs
                .addFlashAttribute("mensaje", "Editado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/sistema/lista-unidad";
    }

    @PostMapping("/eliminar-unidad")
    public String eliminarUnidad(RedirectAttributes redirectAttrs,
                                 @RequestParam(value = "txtid_unidad") Integer idunidad) {
        redirectAttrs
                .addFlashAttribute("mensaje", "Eliminado correctamente")
                .addFlashAttribute("clase", "warning");
        Optional<Unidad> unidadFind = unidad_repo.findById(idunidad);
        unidad_repo.delete(unidadFind.get());
        return "redirect:/sistema/lista-unidad";
    }

    //--------------------------------------------------------------------------------------

    //----------------------CATEGORIA------------------------------------------

    @GetMapping("/lista-categoria")
    public String lista_categoria(Model model) {
        List<Categoria> lista_categoria = categoria_repo.findAll();
        model.addAttribute("categoria", lista_categoria);
        return "sistema/productos/lista_categoria";
    }

    @GetMapping("/ver-agregar-categoria")
    public String ver_agregar_categoria(Model model) {
        Categoria categoria = new Categoria();
        model.addAttribute("categoria", categoria);
        return "sistema/productos/nueva_categoria";
    }

    @PostMapping("/agregar-categoria")
    public String agregar_categoria(@ModelAttribute("categoria") Categoria categoria,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttrs,
                                    HttpSession session) {
        if (bindingResult.hasErrors()) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Error")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/sistema/agregar-categoria";
        }
        try {
            Usuario usu_logueado = (Usuario) session.getAttribute("usuariosession");
            Empleado find_empleado = empleado_dao.findByCorreo(usu_logueado.getUsuario());
            categoria.setCreatedAt(localDate);
            categoria.setUpdatedAt(localDate);
            categoria.setEmpleadoId(find_empleado);
            categoria_repo.save(categoria);
            redirectAttrs
                    .addFlashAttribute("mensaje", "Agregado correctamente")
                    .addFlashAttribute("clase", "success");

            return "redirect:/sistema/lista-categoria";
        } catch (Exception e) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Error")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/sistema/lista-categoria";

        }

    }

    @GetMapping("/ver-editar-categoria/{id_categoria}")
    public String editarCategoria(@PathVariable Integer id_categoria, Model model) {
        Optional<Categoria> categoriaFind = categoria_repo.findById(id_categoria);
        model.addAttribute("categoria", categoriaFind.get());
        return "sistema/productos/editar_categoria";
    }

    @PostMapping("/validar-edicion-categoria/{id_categoria}")
    public String saveEdicionCategoria(
            @RequestParam(value = "txtid_categoria") Integer id_categoria,
            @ModelAttribute("categoria") @Validated Categoria categoria,
            BindingResult bindingResult,
            RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            if (categoria.getId() != null) {
                redirectAttrs
                        .addFlashAttribute("mensaje", "Error")
                        .addFlashAttribute("clase", "warning");
                return "redirect:/sistema/ver-editar-categoria/" + id_categoria;
            }
            redirectAttrs
                    .addFlashAttribute("mensaje", "Error")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/sistema/ver-editar-categoria/" + id_categoria;
        }
        Optional<Categoria> categoriaFind = categoria_repo.findById(id_categoria);
        categoriaFind.get().setNombre(categoria.getNombre());
        categoriaFind.get().setDescripcion(categoria.getDescripcion());
        categoria_repo.save(categoriaFind.get());
        redirectAttrs
                .addFlashAttribute("mensaje", "Editado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/sistema/lista-categoria";
    }

    @PostMapping("/eliminar-categoria")
    public String eliminarCategoria(RedirectAttributes redirectAttrs,
                                    @RequestParam(value = "txtid_categoria") Integer idcategoria) {
        redirectAttrs
                .addFlashAttribute("mensaje", "Eliminado correctamente")
                .addFlashAttribute("clase", "warning");
        Optional<Categoria> categoriaFind = categoria_repo.findById(idcategoria);
        categoria_repo.delete(categoriaFind.get());
        return "redirect:/sistema/lista-categoria";
    }

    //--------------------------------------------------------------------------------------

    //----------------------PRODUCTO------------------------------------------

    @GetMapping("/lista-producto")
    public String lista_producto(Model model) {
        List<Producto> lista_producto = producto_repo.findAll();
        model.addAttribute("producto", lista_producto);
        return "sistema/productos/lista_producto";
    }

    @GetMapping("/ver-agregar-producto")
    public String ver_agregar_producto(Model model) {
        Producto producto = new Producto();
        List<Categoria> lista_categoria = categoria_repo.findAll();
        model.addAttribute("producto", producto);
        model.addAttribute("categoria", lista_categoria);
        return "sistema/productos/nuevo_producto";
    }

    @PostMapping("/agregar-producto")
    public String agregar_producto(@ModelAttribute("producto") Producto producto,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttrs,
                                   HttpSession session) {
        if (bindingResult.hasErrors()) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Error")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/sistema/agregar-producto";
        }
        try {
            Usuario usu_logueado = (Usuario) session.getAttribute("usuariosession");
            Empleado find_empleado = empleado_dao.findByCorreo(usu_logueado.getUsuario());
            producto.setCreatedAt(localDate);
            producto.setUpdatedAt(localDate);
            producto.setEmpleadoId(find_empleado);
            producto.setEstado(true);
            producto_repo.save(producto);
            redirectAttrs
                    .addFlashAttribute("mensaje", "Agregado correctamente")
                    .addFlashAttribute("clase", "success");

            return "redirect:/sistema/lista-producto";
        } catch (Exception e) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Error")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/sistema/lista-producto";

        }

    }

    @GetMapping("/ver-editar-producto/{id_producto}")
    public String editarProducto(@PathVariable Integer id_producto, Model model) {
        Optional<Producto> productoFind = producto_repo.findById(id_producto);
        List<Categoria> lista_categoria = categoria_repo.findAll();
        model.addAttribute("producto", productoFind.get());
        model.addAttribute("categoria", lista_categoria);
        return "sistema/productos/editar_producto";
    }

    @PostMapping("/validar-edicion-producto/{id_producto}")
    public String saveEdicionProducto(
            @RequestParam(value = "txtid_producto") Integer id_producto,
            @RequestParam(value = "producto_categoria") Integer producto_categoria,
            @ModelAttribute("producto") @Validated Producto producto,
            @ModelAttribute("categoria") @Validated Categoria categoria,

            BindingResult bindingResult,
            RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            if (producto.getId() != null) {
                redirectAttrs
                        .addFlashAttribute("mensaje", "Error")
                        .addFlashAttribute("clase", "warning");
                return "redirect:/sistema/ver-editar-producto/" + id_producto;
            }
            redirectAttrs
                    .addFlashAttribute("mensaje", "Error")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/sistema/ver-editar-producto/" + id_producto;
        }
        Optional<Producto> productoFind = producto_repo.findById(id_producto);
        Optional<Categoria> categoriaFind = categoria_repo.findById(producto_categoria);
        productoFind.get().setNombre(producto.getNombre());
        productoFind.get().setDescripcion(producto.getDescripcion());
        productoFind.get().setCategoriaId(categoriaFind.get());
        producto_repo.save(productoFind.get());
        redirectAttrs
                .addFlashAttribute("mensaje", "Editado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/sistema/lista-producto";
    }

    @PostMapping("/eliminar-producto")
    public String eliminarProducto(RedirectAttributes redirectAttrs,
                                   @RequestParam(value = "txtid_producto") Integer idproducto) {
        redirectAttrs
                .addFlashAttribute("mensaje", "Eliminado correctamente")
                .addFlashAttribute("clase", "warning");
        Optional<Producto> productoFind = producto_repo.findById(idproducto);
        producto_repo.delete(productoFind.get());
        return "redirect:/sistema/lista-producto";
    }

    //--------------------------------------------------------------------------------------

    //----------------------RUTA------------------------------------------

    @GetMapping("/lista-ruta")
    public String lista_ruta(Model model) {
        List<Ruta> lista_ruta = ruta_repo.findAll();
        model.addAttribute("ruta", lista_ruta);
        return "sistema/rutas/lista_ruta";
    }

    @GetMapping("/ver-agregar-ruta")
    public String ver_agregar_ruta(Model model) {
        Ruta ruta = new Ruta();
        List<Almacen> lista_almacen = almacen_repo.findAll();
        model.addAttribute("ruta", ruta);
        model.addAttribute("almacen", lista_almacen);
        return "sistema/rutas/nueva_ruta";
    }

    @PostMapping("/agregar-ruta")
    public String agregar_ruta(@ModelAttribute("ruta") Ruta ruta,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttrs,
                               HttpSession session) {
        if (bindingResult.hasErrors()) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Error")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/sistema/agregar-ruta";
        }
        try {
            Usuario usu_logueado = (Usuario) session.getAttribute("usuariosession");
            Empleado find_empleado = empleado_dao.findByCorreo(usu_logueado.getUsuario());
            ruta.setCreatedAt(localDate);
            ruta.setUpdatedAt(localDate);
            ruta.setEmpleadoId(find_empleado);
            ruta_repo.save(ruta);
            redirectAttrs
                    .addFlashAttribute("mensaje", "Agregado correctamente")
                    .addFlashAttribute("clase", "success");

            return "redirect:/sistema/lista-ruta";
        } catch (Exception e) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Error")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/sistema/lista-ruta";

        }

    }

    @GetMapping("/ver-editar-ruta/{id_ruta}")
    public String editarRuta(@PathVariable Integer id_ruta, Model model) {
        Optional<Ruta> rutaFind = ruta_repo.findById(id_ruta);
        List<Almacen> lista_almacen = almacen_repo.findAll();
        model.addAttribute("ruta", rutaFind.get());
        model.addAttribute("almacen", lista_almacen);
        return "sistema/rutas/editar_ruta";
    }

    @PostMapping("/validar-edicion-ruta/{id_ruta}")
    public String saveEdicionRuta(
            @RequestParam(value = "txtid_ruta") Integer id_ruta,
            @RequestParam(value = "ruta_almacen") Integer ruta_almacen,
            @ModelAttribute("ruta") @Validated Ruta ruta,
            BindingResult bindingResult,
            RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            if (ruta.getId() != null) {
                redirectAttrs
                        .addFlashAttribute("mensaje", "Error")
                        .addFlashAttribute("clase", "warning");
                return "redirect:/sistema/ver-editar-ruta/" + id_ruta;
            }
            redirectAttrs
                    .addFlashAttribute("mensaje", "Error")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/sistema/ver-editar-ruta/" + id_ruta;
        }
        Optional<Ruta> rutaFind = ruta_repo.findById(id_ruta);
        Optional<Almacen> almacenFind = almacen_repo.findById(ruta_almacen);
        rutaFind.get().setDestino(ruta.getDestino());
        rutaFind.get().setDistancia(ruta.getDistancia());
        rutaFind.get().setDuracion_estimada(ruta.getDuracion_estimada());
        rutaFind.get().setTarifa_base(ruta.getTarifa_base());
        rutaFind.get().setAlmacenId(almacenFind.get());
        ruta_repo.save(rutaFind.get());
        redirectAttrs
                .addFlashAttribute("mensaje", "Editado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/sistema/lista-ruta";
    }

    @PostMapping("/eliminar-ruta")
    public String eliminarRuta(RedirectAttributes redirectAttrs,
                               @RequestParam(value = "txtid_ruta") Integer idruta) {
        redirectAttrs
                .addFlashAttribute("mensaje", "Eliminado correctamente")
                .addFlashAttribute("clase", "warning");
        Optional<Ruta> rutaFind = ruta_repo.findById(idruta);
        ruta_repo.delete(rutaFind.get());
        return "redirect:/sistema/lista-ruta";
    }

    //------------------------------------------------------------

    //--------------------------------INGRESOS------------------------------
    @GetMapping("/lista-ingresos")
    public String ver_ingresos(Model model){
        List<Ingreso> lista_ingresos = ingreso_repo.findAll();
        model.addAttribute("ingresos", lista_ingresos);
        return "sistema/ingresos/lista_ingresos";
    }

    @GetMapping("/ver-agregar-ingreso")
    public String ver_agregar_ingreso(Model model){
        Ingreso new_ingreso = new Ingreso();
        List<Producto> lista_productos = producto_repo.findAll();
        List<Proveedor> lista_proveedores = proveedor_service.listaProveedores();
        List<Unidad> lista_unidades = unidad_repo.findAll();
        List<Almacen> lista_almacenes = almacen_repo.findAll();

        model.addAttribute("ingreso", new_ingreso);
        model.addAttribute("productos", lista_productos);
        model.addAttribute("proveedores", lista_proveedores);
        model.addAttribute("unidades", lista_unidades);
        model.addAttribute("almacenes", lista_almacenes);
        return "sistema/ingresos/agregar_ingreso";
    }

    @PostMapping("/agregar-ingreso")
    public String guardar_ingreso(
                @ModelAttribute("ingreso") Ingreso ingreso,
                @RequestParam("codigo[]") Integer[] codigo,
                @RequestParam("prod_id[]") Integer[] prod_id,
                @RequestParam("alm_id[]") Integer[] alm_id,
                @RequestParam("prov_id[]") Integer[] prov_id,
                @RequestParam("uni_id[]") Integer[] uni_id,
                @RequestParam("cantidad[]") Integer[] cantidad,
                @RequestParam("descripcion[]") String[] descripcion,
                BindingResult bindingResult,
                RedirectAttributes redirectAttrs,
                HttpSession session){
            if (bindingResult.hasErrors()) {
                redirectAttrs
                        .addFlashAttribute("mensaje", bindingResult)
                        .addFlashAttribute("clase", "warning");
                return "redirect:/sistema/agregar-ingreso";
            }
            try {
                Ingreso new_ingreso = new Ingreso();
                new_ingreso.setCodigo(codigo[0]);
                new_ingreso.setDescripcion(ingreso.getDescripcion());
                new_ingreso.setCreatedAt(localDate);
                new_ingreso.setUpdatedAt(localDate);
                ingreso_repo.save(new_ingreso);


                Usuario usu_logueado = (Usuario) session.getAttribute("usuariosession");
                Empleado find_empleado = empleado_dao.findByCorreo(usu_logueado.getUsuario());
                for (int i = 0; i < prod_id.length; i++) {
                    Optional<Producto> find_producto = producto_repo.findById(prod_id[i]);
                    Optional<Almacen> find_almacen = almacen_repo.findById(alm_id[i]);
                    Optional<Proveedor> find_proveedor = proveedor_repo.findById(prov_id[i]);
                    Optional<Unidad> find_unidad = unidad_repo.findById(uni_id[i]);
                    
                    almacen_productos new_almacen_prod = new almacen_productos();
                    new_almacen_prod.setCodigo(codigo[i]);
                    new_almacen_prod.setStock(cantidad[i]);
                    new_almacen_prod.setEmpleadoId(find_empleado);
                    new_almacen_prod.setProductoId(find_producto.get());
                    new_almacen_prod.setAlmacenId(find_almacen.get());
                    new_almacen_prod.setProveedorId(find_proveedor.get());
                    new_almacen_prod.setCreatedAt(localDate);
                    new_almacen_prod.setUpdatedAt(localDate);
                    almacen_prod_repo.save(new_almacen_prod);

                    producto_unidades new_prod_unidad = new producto_unidades();
                    new_prod_unidad.setProductoId(find_producto.get());
                    new_prod_unidad.setUnidadId(find_unidad.get());
                    new_prod_unidad.setDescripcion(descripcion[i]);
                    new_prod_unidad.setCreatedAt(localDate);
                    new_prod_unidad.setUpdatedAt(localDate);
                    producto_unidad_repo.save(new_prod_unidad);
                    
                }
                redirectAttrs
                    .addFlashAttribute("mensaje", "Agregado correctamente")
                    .addFlashAttribute("clase", "success");

                return "redirect:/sistema/lista-ingresos";
            } catch (Exception e) {
                redirectAttrs
                    .addFlashAttribute("mensaje", e)
                    .addFlashAttribute("clase", "warning");

                return "redirect:/sistema/ver-agregar-ingreso";
            }
                

    }

    //----------REGISTRO ENVIO -------------------------------------
    @GetMapping("/registrar-envio")
    public String showregistrarEnvio(Model model) {
        try {
            Envio nuevo_envio = new Envio();
            List<Cliente> clientes = cliente_repo.findAll();
            List<Vehiculo> vehiculos = vehiculo_repo.findAll();
            List<Producto> productos = producto_repo.findAll();
            List<Conductor> conductores = conductor_repo.findAll();
            List<Ingreso> lista_ingresos = ingreso_repo.findAll();
            List<Unidad> lista_unidades = unidad_repo.findAll();
            List<Almacen> lista_almacenes = almacen_repo.findAll();
        

            model.addAttribute("envio", nuevo_envio);
            model.addAttribute("clientes", clientes);
            model.addAttribute("vehiculos", vehiculos);
            model.addAttribute("productos", productos);
            model.addAttribute("conductores", conductores);
            model.addAttribute("ingresos", lista_ingresos);
            model.addAttribute("unidades", lista_unidades);
            model.addAttribute("almacenes", lista_almacenes);

            return "sistema/envios/registro_envio";
        } catch (Exception e) {
            return "redirect:/sistema/inicio";
        }

    }

    @PostMapping("/definir-envio")
    public String registrarEnvio(
            @ModelAttribute("envio") Envio envio,
            @RequestParam("prod_id[]") Integer[] prod_id, 
            @RequestParam("cantidad[]") Integer[] cantidad,
            @RequestParam("precio[]") Double[] precio,
            BindingResult bindingResult,
            RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            redirectAttrs
                    .addFlashAttribute("mensaje", bindingResult)
                    .addFlashAttribute("clase", "warning");
            return "redirect:/sistema/registrar-envio";
        }
        try {
            envio.setFecha_recogida(localDate);
            envio.setCreatedAt(localDate);
            envio.setUpdatedAt(localDate);
            envio.setEstado(true);
            envio_repo.save(envio);

            for (int i = 0; i < prod_id.length; i++) {
                Optional<Producto> find_producto = producto_repo.findById(prod_id[i]);
                envio_productos nuevo_envio_prod = new envio_productos();
                nuevo_envio_prod.setEnvioId(envio);
                nuevo_envio_prod.setProductoId(find_producto.get());
                nuevo_envio_prod.setCantidad(cantidad[i]);
                nuevo_envio_prod.setPrecio(precio[i]);
                nuevo_envio_prod.setCreatedAt(localDate);
                nuevo_envio_prod.setUpdatedAt(localDate);
                envio_prod_repo.save(nuevo_envio_prod);
            }
            redirectAttrs
                    .addFlashAttribute("mensaje", "Agregado correctamente")
                    .addFlashAttribute("clase", "success");

            return "redirect:/sistema/inicio";
        } catch (Exception e) {
            redirectAttrs
                    .addFlashAttribute("mensaje", e)
                    .addFlashAttribute("clase", "warning");
            return "redirect:/sistema/inicio";

        }

    }

    //----------FACTURAS -------------------------------------
    @GetMapping("/ver-agregar-factura")
    public String showAgregarFactura(Model model) {
        try {
            Factura factura = new Factura();

            model.addAttribute("factura", factura);

            return "sistema/facturas/nueva_factura";
        } catch (Exception e) {
            return "redirect:/sistema/inicio";
        }

    }

    @PostMapping("/registrar-factura")
    public String registrarFactura(
            @ModelAttribute("factura") Factura factura,
            BindingResult bindingResult,
            RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            redirectAttrs
                    .addFlashAttribute("mensaje", bindingResult)
                    .addFlashAttribute("clase", "warning");
            return "redirect:/sistema/listar-factura";
        }
        try {
            factura.setCreatedAt(localDate);
            factura.setUpdatedAt(localDate);
            factura_repo.save(factura);
            redirectAttrs
                    .addFlashAttribute("mensaje", "Agregada correctamente")
                    .addFlashAttribute("clase", "Warning problem");

            return "redirect:/sistema/listar-factura";
        } catch (Exception e) {
            redirectAttrs
                    .addFlashAttribute("mensaje", e)
                    .addFlashAttribute("clase", "warning");
            return "redirect:/sistema/inicio";

        }

    }

    @GetMapping("/listar-factura")
    public String listarFacturas(Model model) {
        try {
            List<Factura> facturas = factura_repo.findAll();

            model.addAttribute("facturas", facturas);

            return "sistema/facturas/lista_facturas";
        } catch (Exception e) {
            return "redirect:/sistema/inicio";
        }

    }
    //--------------------------------------------------------------------------------------
}
