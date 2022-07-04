package com.plazavea.webservice.dto;

import java.util.List;

import lombok.Data;

@Data
public class TipoReq {
    private String nombre;
    private List<SubTipoReq> subtipos;
}
