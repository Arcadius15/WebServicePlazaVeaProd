package com.plazavea.webservice.service;


import com.plazavea.webservice.model.Producto;
import com.plazavea.webservice.model.SubCategoria;
import com.plazavea.webservice.repository.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public void eliminar(String id) {
        repository.deleteById(id);
        
    }

    @Override
    public Page<Producto> listar(Pageable page) {
        
        return repository.findAll(page);
    }

    @Override
    @Transactional(readOnly = true)
    public Producto buscar(String id) {
        
        return repository.findById(id).orElse(null);
    }

    @Override
    public Page<Producto> listarPorSubCat(SubCategoria subCategoria, Pageable page) {
        
        return repository.findBySubtipo_Tipo_Subcategoria(subCategoria, page);
    }
    
}
