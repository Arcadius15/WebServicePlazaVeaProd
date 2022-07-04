package com.plazavea.webservice.dto;

import java.util.List;

import lombok.Data;

@Data
public class SubcategoriaReq {
    private Long idSubcategoria;
    private String nombre;
    private String urlFoto;
    private List<TipoReq> tipos;
}
