package com.plazavea.webservice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Data;


@Data
@Embeddable
public class OrdenDetalleKey implements Serializable{

    @Column(name = "id_orden")
    private int idOrden;

    @Column(name = "id_producto")
    private int idProducto;
}