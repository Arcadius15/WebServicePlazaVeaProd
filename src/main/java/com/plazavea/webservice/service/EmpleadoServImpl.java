package com.plazavea.webservice.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.plazavea.webservice.model.Empleado;
import com.plazavea.webservice.repository.EmpleadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoServImpl implements EmpleadoServ{

    @Autowired
    private EmpleadoRepository repository;

    @Override
    @Transactional
    public void registrar(Empleado empleado) {
        repository.save(empleado);
    }

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
    public List<Empleado> listar() {
        
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Empleado buscar(String id) {
        return repository.findById(id).orElse(null);
    }
    
}
