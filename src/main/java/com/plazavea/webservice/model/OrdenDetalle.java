package com.plazavea.webservice.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = {"orden","producto"})
@Entity
public class OrdenDetalle {

    @EmbeddedId
    private OrdenDetalleKey id;

    @Column
    private int cantidad;
    @Column
    private Double precio;
    
    @ManyToOne
    @MapsId("idOrden")
    @JoinColumn(name = "id_orden")
    private Orden orden;

    @ManyToOne
    @MapsId("idProducto")
    @JoinColumn(name = "id_producto")
    private Producto producto;

}
