package com.plazavea.webservice.service;

import java.util.List;

import com.plazavea.webservice.model.Usuario;
import com.plazavea.webservice.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServImpl implements UsuarioServ{

    @Autowired
    private UsuarioRepository repository;

    @Override
    @Transactional
    public void registrar(Usuario usuario) {
        repository.save(usuario);
        
    }

    @Override
    @Transactional
    public void editar(Usuario usuario) {
        repository.saveAndFlush(usuario);
        
    }

    @Override
    @Transactional
    public void eliminar(int id) {
        repository.deleteById(id);
        
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listar() {
        
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario buscar(int id) {
        
        return repository.findById(id).orElse(null);
    }
    
}
