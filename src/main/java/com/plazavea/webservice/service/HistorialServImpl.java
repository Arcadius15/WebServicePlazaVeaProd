package com.plazavea.webservice.service;

import java.util.List;

import com.plazavea.webservice.model.HistorialOrden;
import com.plazavea.webservice.repository.HistorialRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HistorialServImpl implements HistorialServ{

    @Autowired
    private HistorialRepository repository;

    @Override
    @Transactional
    public void registrar(HistorialOrden historialOrden) {
        repository.save(historialOrden);
        
    }

    @Override
    @Transactional
    public void editar(HistorialOrden historialOrden) {
        repository.saveAndFlush(historialOrden);
        
    }

    @Override
    @Transactional
    public void eliminar(int id) {
        repository.deleteById(id);
        
    }

    @Override
    @Transactional(readOnly = true)
    public List<HistorialOrden> listar() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public HistorialOrden buscar(int id) {
        return repository.findById(id).orElse(null);
    }
    
}
