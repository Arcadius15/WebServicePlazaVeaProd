package com.plazavea.webservice.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
public class CategoriaRes {
    private Long idCategoria;
    private String nombre;
    @JsonIgnoreProperties({"tipos"})
    private List<SubcategoriaReq> subcategorias;
}
