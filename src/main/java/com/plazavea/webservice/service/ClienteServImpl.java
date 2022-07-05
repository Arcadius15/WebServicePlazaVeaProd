package com.plazavea.webservice.service;

import com.plazavea.webservice.model.Cliente;
import com.plazavea.webservice.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteServImpl implements ClienteServ{

    @Autowired
    private ClienteRepository repository;

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
    public Page<Cliente> listar(Pageable page) {
        return repository.findAll(page);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente buscar(String id) {
        return repository.findById(id).orElse(null);
    }
    
}
