package com.plazavea.webservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plazavea.webservice.model.Pedido;
import com.plazavea.webservice.repository.PedidoRepository;

@Service
public class PedidoServImpl implements PedidoServ{

    @Autowired
    private PedidoRepository repository;

    @Override
    @Transactional
    public void registrar(Pedido pedido) {
        repository.save(pedido);
        
    }

    @Override
    @Transactional
    public void editar(Pedido pedido) {
        repository.saveAndFlush(pedido);
        
    }

    @Override
    @Transactional
    public void eliminar(String id) {
        repository.deleteById(id);
        
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pedido> listar() {
        
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Pedido buscar(String id) {
        
        return repository.findById(id).orElse(null);
    }
    
}
