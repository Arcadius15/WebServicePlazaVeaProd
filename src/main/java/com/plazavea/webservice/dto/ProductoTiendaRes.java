package com.plazavea.webservice.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProductoTiendaRes {
    private ProductoTiendaKeyRes id;
    private int stock;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaActualizacion;
}

@NoArgsConstructor
@Getter
@Setter
class ProductoTiendaKeyRes{
    private String idProducto;
    private String idTienda;
}