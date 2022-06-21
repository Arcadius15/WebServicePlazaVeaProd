package com.plazavea.webservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "especificaciones")
public class Especificaciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEspec;
    @Column
    private String especificacion;

    @ManyToOne
    @JoinColumn(name = "id_producto",
        foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_producto) references producto(id_producto)"))
    private Producto producto;

}
