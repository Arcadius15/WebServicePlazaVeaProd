package com.plazavea.webservice.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "categoria")
public class Categoria {
    private int idCategoria;
    private String nombre;
}
