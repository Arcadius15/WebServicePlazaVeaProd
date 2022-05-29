package com.plazavea.webservice.service;

import java.util.List;

import com.plazavea.webservice.model.Tipo;
import com.plazavea.webservice.repository.TipoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TipoServImpl implements TipoServ{

    @Autowired
    private TipoRepository repository;

    @Override
    @Transactional
    public void registrar(Tipo tipo) {
        repository.save(tipo);
        
    }

    @Override
    @Transactional
    public void editar(Tipo tipo) {
        repository.saveAndFlush(tipo);
        
    }

    @Override
    @Transactional
    public void eliminar(int id) {
        repository.deleteById(id);
        
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tipo> listar() {
        
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Tipo buscar(int id) {
        
        return repository.findById(id).orElse(null);
    }
    
}
