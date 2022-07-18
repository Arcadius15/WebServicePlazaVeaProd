package com.plazavea.webservice.dto;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.plazavea.webservice.enums.OrdenStatus;

import lombok.Data;

@Data
public class OrdenRes {
    private String idOrden;
    private Double monto;
    private Double igv;
    private Double total;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fecha;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaEntrega;
    private String direccion;
    private String formaPago;
    private int tipoFop;
    @Enumerated(EnumType.STRING)
    private OrdenStatus status;
    private ClienteOrdenRequest cliente;
    private TiendaOrdenRequest tienda;
    private Set<OrdenDetalleORequest> ordendetalle;
}

@Data
class ClienteOrdenRes{
    private String idCliente;
}

@Data
class TiendaOrdenRes{
    private String idTienda;
}

@Data
class OrdenDetalleORes{
    private ProductoODreq producto;
    private int cantidad;
    private Double precio;

}

@Data
class ProductoODres{
    private String idProducto;
}



