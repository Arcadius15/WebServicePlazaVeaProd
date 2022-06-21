package com.plazavea.webservice.repository;

import com.plazavea.webservice.model.Subtipo;
import com.plazavea.webservice.model.Tipo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubTipoRepository extends JpaRepository<Subtipo,Integer>{

    List<Subtipo> findByTipo(Tipo tipo);
}
