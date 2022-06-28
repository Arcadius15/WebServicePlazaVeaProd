package com.plazavea.webservice.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.plazavea.webservice.model.Categoria;
import com.plazavea.webservice.model.Tipo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubcategoriaRes {
    private int idSubcategoria;
    private String nombre;
    private String urlFoto;
    @JsonIgnoreProperties({"subcategorias"})
    private Categoria categoria;
    @JsonIgnoreProperties({"subtipos","subcategoria"})
    private List<Tipo> tipos;
}
