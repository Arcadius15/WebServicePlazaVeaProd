package com.plazavea.webservice.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.plazavea.webservice.enums.PedidoCompraStatus;

import lombok.Data;

@Data
@Entity
@Table(name = "pedido_historial")
public class PedidoHistorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private PedidoCompraStatus estado;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate fechaEstado;

    @ManyToOne
    @JoinColumn(name = "id_pedido",
        foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_pedido) references pedido(id_pedido)"))
    private Pedido pedido;


}
