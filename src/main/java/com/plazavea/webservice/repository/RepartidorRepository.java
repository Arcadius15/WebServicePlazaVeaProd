package com.plazavea.webservice.repository;

import com.plazavea.webservice.model.Repartidor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepartidorRepository extends JpaRepository<Repartidor,String>{
    Page<Repartidor> findByTienda_IdTienda(String idTienda,Pageable page);
    List<Repartidor> findByTurnoAndTienda_IdTienda(int turno,String idTienda);

}
