package com.plazavea.webservice.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProductoTiendaReq {
    
    @NotEmpty
    private String idProducto;
    @NotEmpty
    private String idTienda;
    @NotNull
    private int stock;
}