package com.plazavea.webservice.repository;

import com.plazavea.webservice.model.SubCategoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoriaRepository extends JpaRepository<SubCategoria,Integer>{
    
}
