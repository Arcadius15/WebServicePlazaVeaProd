package com.plazavea.webservice.dto;

import lombok.Data;

@Data
public class DireccionRes {
    private int idDireccion;
    private String direccion;
    private Long latitud;
    private Long longitud;
}
