package com.plazavea.webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plazavea.webservice.model.Descripcion;

@Repository
public interface DescripRepository extends JpaRepository<Descripcion,Integer>{
    
}
