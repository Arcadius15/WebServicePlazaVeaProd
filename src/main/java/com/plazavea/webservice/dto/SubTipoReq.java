package com.plazavea.webservice.dto;

import java.util.List;

import lombok.Data;

@Data
public class SubTipoReq {
    private String nombre;
    private List<ProductoReq> productos;
}
