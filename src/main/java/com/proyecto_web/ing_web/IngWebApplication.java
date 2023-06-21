package com.proyecto_web.ing_web;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.proyecto_web.ing_web.entities.Rol;
import com.proyecto_web.ing_web.entities.Usuario;
import com.proyecto_web.ing_web.servicios.UserService;

@SpringBootApplication
public class IngWebApplication {

	@Autowired
	private UserService usuarioServicio;


	public static void main(String[] args) {
		SpringApplication.run(IngWebApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(){
		return args -> {
			String user = "admin@gmail.com";
			Usuario userExist = usuarioServicio.findByUsuario(user);
			System.out.print(userExist);
			if(userExist == null) {
				Usuario usuario = new Usuario();
				usuario.setUsuario(user);
				usuario.setPassword("admin");;
				usuario.setRol(Arrays.asList(new Rol("ROLE_ADMIN")));
				usuarioServicio.guardar(usuario);
			}else{
				System.out.print("error GAAAAAAAAA");
			}
		};
	}
}
