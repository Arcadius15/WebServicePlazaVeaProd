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
@Table(name = "direccion")
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDireccion;
    @Column
    private String direccion;
    @Column
    private Long latitud;
    @Column
    private Long longitud;
    @ManyToOne
    @JoinColumn(name = "id_cliente",nullable = false,
        foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_cliente) references cliente(id_cliente)"))
    private Cliente cliente;

    
}
