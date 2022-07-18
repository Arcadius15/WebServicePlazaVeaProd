package com.plazavea.webservice.dto;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.plazavea.webservice.enums.OrdenStatus;

import lombok.Data;

@Data
public class OrdenReq {
    @NotNull
    private Double monto;
    @NotNull
    private Double igv;
    @NotNull
    private Double total;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fecha;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaEntrega;
    @NotEmpty
    private String direccion;
    @NotEmpty
    private String formaPago;
    private int tipoFop;
    @Enumerated(EnumType.STRING)
    private OrdenStatus status;
    @Valid
    private ClienteOrdenRequest cliente;
    @Valid
    private TiendaOrdenRequest tienda;
    @NotNull
    private Set<OrdenDetalleORequest> ordendetalle;
}

@Data
class ClienteOrdenRequest{
    @NotNull
    @NotEmpty
    private String idCliente;
}

@Data
class TiendaOrdenRequest{
    @NotNull
    @NotEmpty
    private String idTienda;
}

@Data
class OrdenDetalleORequest{
    @Valid
    private ProductoODreq producto;
    @NotNull
    private int cantidad;
    @NotNull
    private Double precio;

}

@Data
class ProductoODreq{
    @NotEmpty
    private String idProducto;
}
