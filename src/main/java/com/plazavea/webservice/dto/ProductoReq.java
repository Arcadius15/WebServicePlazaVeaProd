package com.plazavea.webservice.dto;

import java.util.List;

import lombok.Data;

@Data
public class ProductoReq {
    
    private String nombre;
    
    private String imagenUrl;
    
    private Double precioRegular;
    
    private Double precioOferta;
    
    private boolean oferta;

    private List<PromocionReq> promociones;
    private List<EspecificacionesReq> especificaciones;
    private List<DescripcionReq> descripciones;
    
}
