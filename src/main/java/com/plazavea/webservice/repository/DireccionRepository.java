package com.plazavea.webservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plazavea.webservice.model.Direccion;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion,Integer>{
    Optional<List<Direccion>> findByCliente_IdCliente(String id);
}
