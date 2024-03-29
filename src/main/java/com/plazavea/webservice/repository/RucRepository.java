package com.plazavea.webservice.repository;

import com.plazavea.webservice.model.Ruc;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RucRepository extends JpaRepository<Ruc,Integer>{
    Optional<List<Ruc>> findByCliente_IdCliente(String idCliente);
}
