package com.plazavea.webservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
@Table(name="promocion")
public class Promocion {
    @Id
    @SequenceGenerator(name = "prom_seq", sequenceName = "prom_seq", allocationSize = 1)
    @GeneratedValue(generator = "prom_seq")
    private int idProm;
    @Column(length = 800)
    private String condicion;

    @ManyToOne
    @JoinColumn(name = "id_producto",
        foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_producto) references producto(id_producto)"))
    private Producto producto;
}
