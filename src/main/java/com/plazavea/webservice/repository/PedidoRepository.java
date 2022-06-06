package com.plazavea.webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plazavea.webservice.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,String>{
    
}
