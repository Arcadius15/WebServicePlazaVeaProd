package com.plazavea.webservice.repository;

import com.plazavea.webservice.model.SubCategoria;
import com.plazavea.webservice.model.Tipo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoRepository extends JpaRepository<Tipo,Integer>{
    
    List<Tipo> findBySubcategoria(SubCategoria subcategoria);
}
