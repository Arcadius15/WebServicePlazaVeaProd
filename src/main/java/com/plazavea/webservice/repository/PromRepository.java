package com.plazavea.webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plazavea.webservice.model.Promocion;

@Repository
public interface PromRepository extends JpaRepository<Promocion,Integer>{
    
}
