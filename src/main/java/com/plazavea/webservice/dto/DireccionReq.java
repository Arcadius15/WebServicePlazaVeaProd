package com.plazavea.webservice.dto;

import lombok.Data;

@Data
public class DireccionReq {
    private String direccion;
    private Long latitud;
    private Long longitud;
    
}
