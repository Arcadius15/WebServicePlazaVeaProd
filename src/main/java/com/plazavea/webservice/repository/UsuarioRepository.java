package com.plazavea.webservice.repository;

import com.plazavea.webservice.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{

    Usuario findByEmail(String email);
    
}
