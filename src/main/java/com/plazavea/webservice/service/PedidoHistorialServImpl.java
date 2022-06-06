package com.plazavea.webservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plazavea.webservice.model.PedidoHistorial;
import com.plazavea.webservice.repository.PedidoHistorialRepository;

@Service
public class PedidoHistorialServImpl implements PedidoHistorialServ{

    @Autowired
    private PedidoHistorialRepository repository;

    @Override
    @Transactional
    public void registrar(PedidoHistorial hpedido) {
        repository.save(hpedido);
        
    }

    @Override
    @Transactional
    public void editar(PedidoHistorial hpedido) {
        repository.saveAndFlush(hpedido);
        
    }

    @Override
    @Transactional
    public void eliminar(int id) {
        repository.deleteById(id);
        
    }

    @Override
    @Transactional(readOnly = true)
    public List<PedidoHistorial> listar() {
        
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public PedidoHistorial buscar(int id) {
        
        return repository.findById(id).orElse(null);
    }
    
}
