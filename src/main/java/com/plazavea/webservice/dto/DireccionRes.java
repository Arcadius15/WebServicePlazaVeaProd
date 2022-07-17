package com.plazavea.webservice.dto;

import lombok.Data;

@Data
public class DireccionRes {
    private int idDireccion;
    private String direccion;
    private Double latitud;
    private Double longitud;
}
