package com.plazavea.webservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.ForeignKey;

import lombok.Data;

@Data
@Entity
@Table(name = "ruc")
public class Ruc {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRuc;
    @Column
    private String numeroRuc;

    @ManyToOne
    @JoinColumn(name = "id_cliente",nullable = false,
        foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (id_cliente) references cliente(id_cliente)"))
    private Cliente cliente;
    
}
