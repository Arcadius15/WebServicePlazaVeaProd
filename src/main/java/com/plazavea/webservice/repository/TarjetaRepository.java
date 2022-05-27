package com.plazavea.webservice.repository;

import com.plazavea.webservice.model.Tarjeta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta,Integer>{
    
}
