package com.plazavea.webservice.utils;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.plazavea.webservice.security.dto.UsuarioReq;
import com.plazavea.webservice.security.enums.Roles;
import com.plazavea.webservice.security.model.Rol;
import com.plazavea.webservice.security.service.RolServ;
import com.plazavea.webservice.security.service.UserDetService;
import com.plazavea.webservice.security.service.UsuarioServ;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CrearRoles implements CommandLineRunner{
    
    @Autowired
    private RolServ service;

    @Autowired
    private UsuarioServ serviceUser;

    @Autowired
    private UserDetService serviceUserDet;

    @Override
    public void run(String... args) throws Exception {
        if (service.listar().isEmpty()) {
            for (Roles rol : Roles.values()) {
                log.info(rol.name());
                service.save(new Rol(rol));
            }
            log.info("Roles Creados");
        }
        if (!serviceUser.existByEmail("diegovic99@hotmail.com")) {
            Set<String> rol = new HashSet<>();
            rol.add("master"); 
            UsuarioReq master = new UsuarioReq("diegovic99@hotmail.com", "123456", rol);
            serviceUserDet.save(master);
        }
        log.info(System.getenv("SPRING_PROFILES_ACTIVE"));
    }


}
