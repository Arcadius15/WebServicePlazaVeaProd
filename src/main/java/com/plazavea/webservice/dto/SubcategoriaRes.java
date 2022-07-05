package com.plazavea.webservice.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
    @JsonIgnoreProperties({"subtipos"})
    private List<TipoRes> tipos;
}
