package com.plazavea.webservice.repository;

import com.plazavea.webservice.model.Orden;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenRepository extends JpaRepository<Orden,String>{
    Page<Orden> findByCliente_IdCliente(String cliente,Pageable page);
}
