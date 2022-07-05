package com.plazavea.webservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
public class DescripcionRes {
    private Long idDescripcion;
    private String descripcion;
    @JsonIgnoreProperties({"especificaciones","descripciones","promociones"})
    private ProductoRes producto;
}
