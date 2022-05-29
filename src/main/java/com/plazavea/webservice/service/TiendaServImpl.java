package com.plazavea.webservice.service;

import java.util.List;

import com.plazavea.webservice.model.Tienda;
import com.plazavea.webservice.repository.TiendaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TiendaServImpl implements TiendaServ{

    @Autowired
    private TiendaRepository repository;

    @Override
    @Transactional
    public void registrar(Tienda tienda) {
        repository.save(tienda);
        
    }

    @Override
    @Transactional
    public void editar(Tienda tienda) {
        repository.saveAndFlush(tienda);
        
    }

    @Override
    @Transactional
    public void eliminar(int id) {
        repository.deleteById(id);
        
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tienda> listar() {
        
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Tienda buscar(int id) {
        
        return repository.findById(id).orElse(null);
    }
    
}
