package com.plazavea.webservice.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plazavea.webservice.security.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,String>{

    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
}
