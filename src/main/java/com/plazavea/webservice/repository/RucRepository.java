package com.plazavea.webservice.repository;

import com.plazavea.webservice.model.Ruc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RucRepository extends JpaRepository<Ruc,Integer>{
    
}
