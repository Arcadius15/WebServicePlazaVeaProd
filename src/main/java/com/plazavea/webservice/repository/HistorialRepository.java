package com.plazavea.webservice.repository;

import com.plazavea.webservice.model.HistorialOrden;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialRepository extends JpaRepository<HistorialOrden,Integer>{
    
}
