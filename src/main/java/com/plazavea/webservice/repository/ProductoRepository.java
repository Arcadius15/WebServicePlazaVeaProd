package com.plazavea.webservice.repository;

import com.plazavea.webservice.model.Producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,String>{
    
}
