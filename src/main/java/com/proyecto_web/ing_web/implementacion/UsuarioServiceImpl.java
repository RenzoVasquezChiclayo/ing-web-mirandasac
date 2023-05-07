package com.proyecto_web.ing_web.implementacion;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto_web.ing_web.entities.Rol;
import com.proyecto_web.ing_web.entities.Usuario;
import com.proyecto_web.ing_web.repositorios.IUsuarioRepo;
import com.proyecto_web.ing_web.servicios.UserService;


@Service("UsuarioServiceImpl")
public class UsuarioServiceImpl implements UserService {
    
    @Autowired
    private IUsuarioRepo usuario_repo;

    @Autowired
	private BCryptPasswordEncoder passwordEncoder;

    @Override
	public Usuario findByUsuario(String user) {
		Usuario usuarioresponse = usuario_repo.findByUsuario(user);
		return usuarioresponse;
	}

    @Override
    public Usuario guardar(Usuario user){
        List<Usuario> user_exists = usuario_repo.findAll();
        Usuario new_user;
        if (user_exists.size() == 0) {
            new_user = new Usuario(user.getUsuario(),passwordEncoder.encode(user.getPassword()),Arrays.asList(new Rol("ROLE_ADMIN")));
        }else{
            new_user = new Usuario(user.getUsuario(),passwordEncoder.encode(user.getPassword()),Arrays.asList(new Rol("ROLE_USER")));
        }
        return usuario_repo.save(new_user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario_find = usuario_repo.findByUsuario(username);
        UserDetails user_det = new User(usuario_find.getUsuario(), usuario_find.getPassword(), mapearAutoridadesRoles(usuario_find.getRol()));
        return user_det;
    }

    private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> rol){
		return rol.stream().map(role -> new SimpleGrantedAuthority(role.getDescripcion())).collect(Collectors.toList());
	}

    
}
