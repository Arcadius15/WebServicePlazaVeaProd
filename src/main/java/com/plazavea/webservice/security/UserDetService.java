package com.plazavea.webservice.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.plazavea.webservice.model.Usuario;
import com.plazavea.webservice.repository.UsuarioRepository;

@Service
public class UserDetService implements UserDetailsService{

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario user = repository.findByEmail(email);
        if (user!=null) {
            List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
            user.getRoles().forEach(x-> roles.add(new SimpleGrantedAuthority(x.getRol().name()))); 
            return new User(user.getEmail(),user.getPassword(),roles);
        }else{
            throw new UsernameNotFoundException("El usuario no existe");
        }
    }
    
}
