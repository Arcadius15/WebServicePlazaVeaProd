package com.plazavea.webservice.service;


import org.springframework.transaction.annotation.Transactional;

import com.plazavea.webservice.model.Empleado;
import com.plazavea.webservice.repository.EmpleadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoServImpl implements EmpleadoServ{

    @Autowired
    private EmpleadoRepository repository;

    @Override
    @Transactional
    public void editar(Empleado empleado) {
        repository.saveAndFlush(empleado);
    }

    @Override
    @Transactional
    public void eliminar(String id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Empleado> listar(Pageable page) {
        
        return repository.findAll(page);
    }

    @Override
    @Transactional(readOnly = true)
    public Empleado buscar(String id) {
        return repository.findById(id).orElse(null);
    }
    
}
