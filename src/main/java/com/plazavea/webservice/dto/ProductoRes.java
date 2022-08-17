package com.plazavea.webservice.dto;

import java.util.List;

import lombok.Data;

@Data
public class ProductoRes {
    private String idProducto;
    private String nombre;
    private String imagenUrl;
    private Double precioRegular;
    private Double precioOferta;
    private boolean oferta;

    private List<PromocionRes> promociones;
    private List<EspecificacionesRes> especificaciones;
    private List<DescripcionRes> descripciones;
}
