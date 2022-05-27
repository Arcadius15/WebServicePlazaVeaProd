package com.plazavea.webservice.repository;

import com.plazavea.webservice.model.Repartidor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepartidorRepository extends JpaRepository<Repartidor,Integer>{
    
}
