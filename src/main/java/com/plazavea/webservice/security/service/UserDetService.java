package com.plazavea.webservice.security.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.plazavea.webservice.security.dto.UsuarioReq;
import com.plazavea.webservice.security.enums.Roles;
import com.plazavea.webservice.security.model.Rol;
import com.plazavea.webservice.security.model.Usuario;
import com.plazavea.webservice.security.repository.UsuarioRepository;

@Service
public class UserDetService implements UserDetailsService{

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private RolServ rolServ;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario user = repository.findByEmail(email).get();
        if (user!=null) {
            List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
            user.getRoles().forEach(x-> roles.add(new SimpleGrantedAuthority(x.getRol().name()))); 
            return new User(user.getEmail(),user.getPassword(),roles);
        }else{
            throw new UsernameNotFoundException("El usuario no existe");
        }
    }

    public Usuario save(UsuarioReq userReq){
        Usuario user = new Usuario();
        user.setEmail(userReq.getEmail());
        user.setPassword(encriptador().encode(userReq.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolServ.getByRolNombre(Roles.ROLE_USER).get());
        for (String rolReq : userReq.getRoles()) {
            switch (rolReq.toLowerCase()) {
                case "repartidor":
                    if (userReq.getRepartidor()!=null) {
                        user.setRepartidor(userReq.getRepartidor());
                        roles.add(rolServ.getByRolNombre(Roles.ROLE_DELIVERY).get());
                    }
                    break;
                case "cliente":
                    if (userReq.getCliente()!=null) {
                        user.setCliente(userReq.getCliente());
                        roles.add(rolServ.getByRolNombre(Roles.ROLE_CLIENTE).get());
                    }
                break;
                case "empleado":
                    if (userReq.getEmpleado()!=null) {
                        user.setEmpleado(userReq.getEmpleado());
                        roles.add(rolServ.getByRolNombre(Roles.ROLE_EMPLEADO).get());
                    }
                break;
                case "admin":
                    if (userReq.getEmpleado()!=null) {
                        user.setEmpleado(userReq.getEmpleado());
                        roles.add(rolServ.getByRolNombre(Roles.ROLE_ADMIN).get());
                    }
                break;
                case "master":
                    roles.add(rolServ.getByRolNombre(Roles.ROLE_MASTER).get());
                break;
            }
        }
        if (roles.size()==1) {
            return null;
        }
        return repository.save(user);
    }

    @Bean
	public PasswordEncoder encriptador() {
		return new BCryptPasswordEncoder();
	}
    
}
