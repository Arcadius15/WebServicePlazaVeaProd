package com.plazavea.webservice.service;

import java.util.List;

import com.plazavea.webservice.model.Cliente;
import com.plazavea.webservice.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteServImpl implements ClienteServ{

    @Autowired
    private ClienteRepository repository;

    @Override
    @Transactional
    public void registrar(Cliente cliente) {
        repository.save(cliente);
    }

    @Override
    @Transactional
    public void editar(Cliente cliente) {
        repository.saveAndFlush(cliente);
    }

    @Override
    @Transactional
    public void eliminar(String id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> listar() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente buscar(String id) {
        return repository.findById(id).orElse(null);
    }
    
}
