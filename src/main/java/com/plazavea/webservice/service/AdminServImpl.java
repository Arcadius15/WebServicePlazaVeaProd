package com.plazavea.webservice.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.plazavea.webservice.model.Admin;
import com.plazavea.webservice.repository.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServImpl implements AdminServ{

    @Autowired
    private AdminRepository repository;

    @Override
    @Transactional
    public void registrar(Admin admin) {
        repository.save(admin);
    }

    @Override
    @Transactional
    public void editar(Admin admin) {
        repository.saveAndFlush(admin);
    }

    @Override
    @Transactional
    public void eliminar(int id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Admin> listar() {
        
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Admin buscar(int id) {
        return repository.findById(id).orElse(null);
    }
    
}
