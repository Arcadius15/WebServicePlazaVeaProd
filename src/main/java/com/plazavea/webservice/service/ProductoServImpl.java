package com.plazavea.webservice.service;

import java.util.List;

import com.plazavea.webservice.model.Producto;
import com.plazavea.webservice.repository.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServImpl implements ProductoServ{

    @Autowired
    private ProductoRepository repository;

    @Override
    @Transactional
    public void registrar(Producto producto) {
        repository.save(producto);
        
    }

    @Override
    @Transactional
    public void editar(Producto producto) {
        repository.saveAndFlush(producto);
        
    }

    @Override
    @Transactional
    public void eliminar(int id) {
        repository.deleteById(id);
        
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> listar() {
        
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Producto buscar(int id) {
        
        return repository.findById(id).orElse(null);
    }
    
}
