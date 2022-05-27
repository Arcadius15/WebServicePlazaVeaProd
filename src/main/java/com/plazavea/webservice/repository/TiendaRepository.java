package com.plazavea.webservice.repository;

import com.plazavea.webservice.model.Tienda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiendaRepository extends JpaRepository<Tienda,Integer>{
    
}
