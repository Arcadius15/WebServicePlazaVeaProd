package com.plazavea.webservice.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.plazavea.webservice.model.HistorialOrden;
import com.plazavea.webservice.model.Orden;
import com.plazavea.webservice.model.OrdenDetalle;
import com.plazavea.webservice.repository.OrdenRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrdenServImpl implements OrdenServ{

    @Autowired
    private OrdenRepository repository;

    @Override
    @Transactional
    public void registrar(Orden orden) {
        
        Set<OrdenDetalle> od = new HashSet<OrdenDetalle>();
        List<HistorialOrden> ho = new ArrayList<>();
        HistorialOrden h = new HistorialOrden();
        orden.getOrdendetalle().forEach(od::add);
        h.setDescripcion("Solicitud de Pedido Procesado.");
        h.setEstado(0);
        h.setFechaEstado(LocalDate.now());
        h.setOrden(orden);
        ho.add(h);
        orden.setHistorial(ho);
        orden.setOrdendetalle(od);
        repository.save(orden);
        
    }

    @Override
    @Transactional
    public void editar(Orden orden) {
        
        repository.saveAndFlush(orden);
    }

    @Override
    @Transactional
    public void eliminar(int id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Orden> listar() {
        
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Orden buscar(int id) {
        return repository.findById(id).orElse(null);
    }
    
}
