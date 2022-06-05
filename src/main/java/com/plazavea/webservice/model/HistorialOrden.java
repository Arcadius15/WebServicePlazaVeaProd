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

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
public class HistorialOrden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHistorial;
    @Column
    private int estado;
    @Column
    private String descripcion;
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate fechaEstado;

    @ManyToOne
    @JoinColumn(name = "id_orden",nullable = false,
        foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (id_orden) references orden(id_orden)"))
    private Orden orden;

}