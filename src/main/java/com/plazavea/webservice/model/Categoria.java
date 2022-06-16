package com.plazavea.webservice.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "categoria")
@JsonIgnoreProperties({"subcategorias"})
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategoria;
    @Column
    private String nombre;

    @OneToMany(mappedBy = "categoria",cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<SubCategoria> subcategorias;
}
