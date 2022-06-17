package com.plazavea.webservice.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plazavea.webservice.security.model.Usuario;
import com.plazavea.webservice.security.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioServ {
    @Autowired
    UsuarioRepository usuarioRepository;

    public Optional<Usuario> getByEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

    public boolean existByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public void save(Usuario user){
        usuarioRepository.save(user);
    }
}
