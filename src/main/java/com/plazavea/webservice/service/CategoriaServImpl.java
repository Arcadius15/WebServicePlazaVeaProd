package com.plazavea.webservice.service;

import java.util.List;

import com.plazavea.webservice.model.Categoria;
import com.plazavea.webservice.repository.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaServImpl implements CategoriaServ{

    @Autowired
    private CategoriaRepository repository;

    @Override
    @Transactional
    public void registrar(Categoria categoria) {
        repository.save(categoria);
        
    }

    @Override
    @Transactional
    public void editar(Categoria categoria) {
        repository.saveAndFlush(categoria);
    }

    @Override
    @Transactional
    public void eliminar(int id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> listar() {
        
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Categoria buscar(int id) {
        
        return repository.findById(id).orElse(null);
    }
    
}
