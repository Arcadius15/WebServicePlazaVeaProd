package com.plazavea.webservice.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plazavea.webservice.model.ProductoTienda;
import com.plazavea.webservice.model.ProductoTiendaKey;

@Repository
public interface ProductoTiendaRepository extends JpaRepository<ProductoTienda,ProductoTiendaKey>{
    Optional<ProductoTienda> findByProducto_IdProductoAndTienda_IdTienda(String idProd,String idTienda);
    Page<ProductoTienda> findByProducto_IdProducto(String idProducto,Pageable page);
    Page<ProductoTienda> findByTienda_IdTienda(String idTienda,Pageable page);
    boolean existsByProducto_IdProductoAndTienda_IdTienda(String idProd,String idTienda);
}
