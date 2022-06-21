package com.plazavea.webservice.repository;

import com.plazavea.webservice.model.Categoria;
import com.plazavea.webservice.model.SubCategoria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoriaRepository extends JpaRepository<SubCategoria,Integer>{
    List<SubCategoria> findByCategoria(Categoria categoria);
    
}
