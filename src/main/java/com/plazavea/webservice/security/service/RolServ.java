package com.plazavea.webservice.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plazavea.webservice.security.enums.Roles;
import com.plazavea.webservice.security.model.Rol;
import com.plazavea.webservice.security.repository.RolRepository;

@Service
@Transactional
public class RolServ {
    
    @Autowired
    private RolRepository repository;

    public Optional<Rol> getByRolNombre(Roles rol){
        return repository.findByRol(rol);
    }

    public void save(Rol rol){
        repository.save(rol);
    }

    public List<Rol> listar(){
        return repository.findAll();
    }
}
