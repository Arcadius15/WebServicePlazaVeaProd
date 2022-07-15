package com.plazavea.webservice.service;

import java.util.List;

import com.plazavea.webservice.model.Tarjeta;
import com.plazavea.webservice.repository.TarjetaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TarjetaServImpl implements TarjetaServ{

    @Autowired
    private TarjetaRepository repository;

    @Override
    @Transactional
    public void registrar(Tarjeta tarjeta) {
        repository.save(tarjeta);
        
    }

    @Override
    @Transactional
    public void editar(Tarjeta tarjeta) {
        repository.saveAndFlush(tarjeta);
        
    }

    @Override
    @Transactional
    public void eliminar(int id) {
        repository.deleteById(id);
        
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tarjeta> listar(String idCliente) {
        return repository.findByCliente_IdCliente(idCliente).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Tarjeta buscar(int id) {
        
        return repository.findById(id).orElse(null);
    }
    
}
