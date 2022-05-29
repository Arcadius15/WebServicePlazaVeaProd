package com.plazavea.webservice.service;

import java.util.List;

import com.plazavea.webservice.model.Ruc;
import com.plazavea.webservice.repository.RucRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RucServImpl implements RucServ{

    @Autowired
    private RucRepository repository;

    @Override
    @Transactional
    public void registrar(Ruc ruc) {
        repository.save(ruc);  
    }

    @Override
    @Transactional
    public void editar(Ruc ruc) {
        repository.saveAndFlush(ruc);   
    }

    @Override
    @Transactional
    public void eliminar(int id) {
        repository.deleteById(id);    
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ruc> listar() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Ruc buscar(int id) {
        return repository.findById(id).orElse(null);
    }

}
