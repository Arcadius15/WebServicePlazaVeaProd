package com.plazavea.webservice.utils;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.plazavea.webservice.security.enums.Roles;
import com.plazavea.webservice.security.model.Rol;
import com.plazavea.webservice.security.service.RolServ;

@Component
public class CrearRoles implements CommandLineRunner{
    
    @Autowired
    private RolServ service;

    @Override
    public void run(String... args) throws Exception {
        if (service.listar().isEmpty()) {
            for (Roles rol : Roles.values()) {
                service.save(new Rol(rol));
            }
            System.out.println("Roles Creados");
        }
    }


}
