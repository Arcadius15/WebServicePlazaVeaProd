package com.plazavea.webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plazavea.webservice.model.Especificaciones;

@Repository
public interface EspecsRepository extends JpaRepository<Especificaciones,Integer>{
    
}
