package com.plazavea.webservice.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.plazavea.webservice.security.enums.Roles;
import com.plazavea.webservice.security.model.Rol;
import com.plazavea.webservice.security.service.RolServ;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CrearRoles implements CommandLineRunner{
    
    @Autowired
    private RolServ service;

    @Override
    public void run(String... args) throws Exception {
        if (service.listar().isEmpty()) {
            for (Roles rol : Roles.values()) {
                log.info(rol.name());
                service.save(new Rol(rol));
            }
            log.info("Roles Creados");
        }
    }


}
