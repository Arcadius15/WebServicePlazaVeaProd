package com.plazavea.webservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plazavea.webservice.model.Direccion;
import com.plazavea.webservice.repository.DireccionRepository;

@Service
public class DireccioServImpl implements DireccionServ{

    @Autowired
    private DireccionRepository repository;


    @Override
    @Transactional
    public void guardar(Direccion dir) {
        repository.save(dir);
        
    }

    @Override
    @Transactional
    public void editar(Direccion dir) {
        repository.saveAndFlush(dir);
        
    }

    @Override
    @Transactional
    public void eliminar(int id) {
        repository.deleteById(id);
        
    }

    @Override
    @Transactional(readOnly = true)
    public List<Direccion> listar(String id) {
        return repository.findByCliente_IdCliente(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Direccion buscar(int id) {
        return repository.findById(id).orElse(null);
    }

    
    
}
