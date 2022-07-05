package com.plazavea.webservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



import javax.persistence.ForeignKey;

import lombok.Data;

@Data
@Entity
@Table(name = "descripcion")
public class Descripcion {
    @Id
    @SequenceGenerator(name = "desc_seq", sequenceName = "desc_seq", allocationSize = 1)
    @GeneratedValue(generator = "desc_seq")
    private int idDescripcion;
    @Column(length = 400)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_producto",
        foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_producto) references producto(id_producto)"))
    private Producto producto;

}
