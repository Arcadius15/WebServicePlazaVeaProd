package com.plazavea.webservice.service;

import java.util.List;

import com.plazavea.webservice.model.Proveedor;
import com.plazavea.webservice.repository.ProveedorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProveedorServImpl implements ProveedorServ{

    @Autowired
    private ProveedorRepository repository;

    @Override
    @Transactional
    public void registrar(Proveedor proveedor) {
        repository.save(proveedor);
    }

    @Override
    @Transactional
    public void editar(Proveedor proveedor) {
        repository.saveAndFlush(proveedor);
    }

    @Override
    @Transactional
    public void eliminar(String id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Proveedor> listar() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Proveedor buscar(String id) {
        return repository.findById(id).orElse(null);
    }
    
}
