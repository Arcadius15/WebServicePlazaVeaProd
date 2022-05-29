package com.plazavea.webservice.service;

import java.util.List;

import com.plazavea.webservice.model.Orden;
import com.plazavea.webservice.repository.OrdenRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrdenServImpl implements OrdenServ{

    @Autowired
    private OrdenRepository repository;

    @Override
    @Transactional
    public void registrar(Orden orden) {
        repository.save(orden);
        
    }

    @Override
    @Transactional
    public void editar(Orden orden) {
        
        repository.saveAndFlush(orden);
    }

    @Override
    @Transactional
    public void eliminar(int id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Orden> listar() {
        
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Orden buscar(int id) {
        return repository.findById(id).orElse(null);
    }
    
}
