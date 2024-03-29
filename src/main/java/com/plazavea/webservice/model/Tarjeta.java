package com.plazavea.webservice.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "tarjeta")
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTarjeta;
    @Column
    private int tipo;
    @Column
    private String numeroTarjeta;
    @Column
    private String cvv;
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaCaducidad;
    @Column
    private String nombrePropietario;

    @ManyToOne
    @JoinColumn(name = "id_cliente",nullable = false,
        foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (id_cliente) references cliente(id_cliente)"))
    private Cliente cliente;
    
}
