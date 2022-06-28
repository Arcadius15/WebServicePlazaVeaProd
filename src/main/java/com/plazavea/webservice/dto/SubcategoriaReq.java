package com.plazavea.webservice.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.plazavea.webservice.model.Categoria;
import com.plazavea.webservice.model.Tipo;

import lombok.Data;

@Data
public class SubcategoriaReq {
    private int idSubcategoria;
    private String nombre;
    private String urlFoto;
    private Categoria categoria;
    @JsonIgnoreProperties({"subtipos"})
    private List<Tipo> tipos;
}
