package com.plazavea.webservice.repository;

import com.plazavea.webservice.model.Producto;
import com.plazavea.webservice.model.SubCategoria;
import com.plazavea.webservice.model.Subtipo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,String>{
    
    List<Producto> findBySubtipo(Subtipo subtipo);
    Page<Producto> findBySubtipo_Tipo_Subcategoria(SubCategoria subCategoria,Pageable page);
}
