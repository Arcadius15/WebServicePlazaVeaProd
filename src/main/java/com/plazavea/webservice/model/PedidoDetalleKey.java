package com.plazavea.webservice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class PedidoDetalleKey implements Serializable{
    @Column(name = "id_producto")
    private String idProducto;
    @Column(name = "id_pedido")
    private String idPedido;
}
