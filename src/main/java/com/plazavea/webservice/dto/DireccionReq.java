package com.plazavea.webservice.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class DireccionReq {
    @NotNull
    @NotEmpty
    private String direccion;
    @NotNull
    private Double latitud;
    @NotNull
    private Double longitud;
    @Valid
    private ClienteDReq cliente; 
    
}

@Data
class ClienteDReq{
    @NotNull
    @NotEmpty
    private String idCliente;
}
