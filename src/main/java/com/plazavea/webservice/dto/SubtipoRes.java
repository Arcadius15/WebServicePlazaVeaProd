package com.plazavea.webservice.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
public class SubtipoRes {
    private Long idSubtipo;
    private String nombre;
    @JsonIgnoreProperties({"especificaciones","promociones","descripciones"})
    private List<ProductoReq> productos;
}
