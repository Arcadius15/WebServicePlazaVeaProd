package com.plazavea.webservice.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
public class TipoRes {
    private Long idTipo;
    private String nombre;
    @JsonIgnoreProperties({"productos"})
    private List<SubTipoReq> subtipos;
}
