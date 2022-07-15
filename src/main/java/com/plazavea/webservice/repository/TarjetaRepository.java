package com.plazavea.webservice.repository;

import com.plazavea.webservice.model.Tarjeta;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta,Integer>{
    Optional<List<Tarjeta>> findByCliente_IdCliente(String idCliente);
}
