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


import lombok.Data;

@Data
@Entity
@Table(name = "categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idCategoria;
    @Column
    private String nombre;

    @OneToMany(mappedBy = "categoria",cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<SubCategoria> subcategorias;
}
