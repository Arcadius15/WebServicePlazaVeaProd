package com.plazavea.webservice.repository;

import com.plazavea.webservice.model.Proveedor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor,String>{
    
}
