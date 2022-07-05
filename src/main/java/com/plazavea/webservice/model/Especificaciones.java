package com.plazavea.webservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "especificaciones")
public class Especificaciones {
    @Id
    @SequenceGenerator(name = "spec_seq", sequenceName = "spec_seq", allocationSize = 1)
    @GeneratedValue(generator = "spec_seq")
    private int idEspec;
    @Column(length =  1000)
    private String nombre;
    @Column(length = 8000)
    private String valor;

    @ManyToOne
    @JoinColumn(name = "id_producto",
        foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_producto) references producto(id_producto)"))
    private Producto producto;

}
