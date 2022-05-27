package com.plazavea.webservice.repository;

import com.plazavea.webservice.model.Tipo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoRepository extends JpaRepository<Tipo,Integer>{
    
}
