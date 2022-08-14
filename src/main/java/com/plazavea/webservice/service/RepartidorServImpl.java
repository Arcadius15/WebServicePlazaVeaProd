package com.plazavea.webservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plazavea.webservice.model.Repartidor;
import com.plazavea.webservice.repository.RepartidorRepository;

@Service
public class RepartidorServImpl implements RepartidorServ{

    @Autowired
    private RepartidorRepository repository;

    @Override
    @Transactional(readOnly = false)
    public void editar(Repartidor item) {
        repository.saveAndFlush(item);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Repartidor> listarPorTienda(String idTienda,Pageable page) {
        
        return repository.findByTienda_IdTienda(idTienda, page);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Repartidor> buscarPorTurno(int turno,String idTienda) {
        
        return repository.findByTurnoAndTienda_IdTienda(turno,idTienda);
    }

    @Override
    @Transactional(readOnly = true)
    public Repartidor buscar(String id) {
        return repository.findById(id).orElse(null);
    }
    
}
