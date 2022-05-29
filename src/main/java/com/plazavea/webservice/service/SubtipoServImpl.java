package com.plazavea.webservice.service;

import java.util.List;

import com.plazavea.webservice.model.Subtipo;
import com.plazavea.webservice.repository.SubTipoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubtipoServImpl implements SubtipoServ{

    @Autowired
    private SubTipoRepository repository;

    @Override
    @Transactional
    public void registrar(Subtipo subtipo) {
        repository.save(subtipo);
        
    }

    @Override
    @Transactional
    public void editar(Subtipo subtipo) {
        repository.saveAndFlush(subtipo);
        
    }

    @Override
    @Transactional
    public void eliminar(int id) {
        repository.deleteById(id);
        
    }

    @Override
    @Transactional(readOnly = true)
    public List<Subtipo> listar() {
        
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Subtipo buscar(int id) {
        
        return repository.findById(id).orElse(null);
    }
    
}
