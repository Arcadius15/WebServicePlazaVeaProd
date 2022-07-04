package com.plazavea.webservice.dto;

import java.util.List;

import lombok.Data;

@Data
public class CategoriaReq {
    private String nombre;
    private List<SubcategoriaReq> subcategorias;
    
}
