package com.plazavea.webservice.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "subcategoria")
public class SubCategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSubcategoria;
    @Column
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_categoria",nullable = false,
        foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (id_categoria) references categoria(id_categoria)"))
    private Categoria categoria;

    @OneToMany(mappedBy = "subcategoria")
    private List<Tipo> tipos;
    
}
