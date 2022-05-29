package com.plazavea.webservice.service;

import java.util.List;

import com.plazavea.webservice.model.SubCategoria;
import com.plazavea.webservice.repository.SubCategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubCategoriaServImpl implements SubCategoriaServ{

    @Autowired
    private SubCategoriaRepository repository;

    @Override
    @Transactional
    public void registrar(SubCategoria subCategoria) {
        repository.save(subCategoria);
        
    }

    @Override
    @Transactional
    public void editar(SubCategoria subCategoria) {
        repository.saveAndFlush(subCategoria);
        
    }

    @Override
    @Transactional
    public void eliminar(int id) {
        repository.deleteById(id);
        
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubCategoria> listar() {
        
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public SubCategoria buscar(int id) {
        
        return repository.findById(id).orElse(null);
    }
    
}
